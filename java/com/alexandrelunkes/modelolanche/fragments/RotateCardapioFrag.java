package com.alexandrelunkes.modelolanche.fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexandrelunkes.modelolanche.Itens.ItemTipoCardapio;
import com.alexandrelunkes.modelolanche.R;
import com.alexandrelunkes.modelolanche.interfaces.OnNeedChangePagListener;
import com.alexandrelunkes.modelolanche.interfaces.OnRequestComanda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre Lunkes on 04/10/2016.
 */
public class RotateCardapioFrag extends Fragment implements FragmentManager.OnBackStackChangedListener
,OnNeedChangePagListener {


    private boolean mShowingBack = false;
    private OnRequestComanda callBack;
    private List<ItemTipoCardapio> cardapio;

    public static RotateCardapioFrag newInstance(List<ItemTipoCardapio> cardapio) {

        Bundle args = new Bundle();

        String item = "item";

        for(int i = 0; i<cardapio.size(); i++){
            args.putSerializable(item + Integer.toString(i),cardapio.get(i));
        }
        RotateCardapioFrag fragment = new RotateCardapioFrag();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_rotate_cardapio_layout,null);
        cardapio = new ArrayList<>();

        ItemTipoCardapio aux = null;
        int i = 0;

        do{
            aux = (ItemTipoCardapio) getArguments().getSerializable("item"+Integer.toString(i));
            if(aux!=null)cardapio.add(aux);
            i++;
        }while (aux!=null);

        List<String> nomesTipos = new ArrayList<>();
        List<Integer> idsImagem = new ArrayList<>();;

        for(ItemTipoCardapio itc: cardapio){
              nomesTipos.add(itc.getNome());
              idsImagem.add(itc.getIdImage());
        }


        TipoDeCardapioFrag tipoDeCardapioFrag = new TipoDeCardapioFrag();
        tipoDeCardapioFrag.setNeedChangePagListener(this);
        tipoDeCardapioFrag.setValues(nomesTipos,idsImagem);

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction()
                    .replace(R.id.container,tipoDeCardapioFrag)
                    .commit();
        } else {
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        }

        getFragmentManager().addOnBackStackChangedListener(this);

        return v;
    }


    @Override
    public void onBackStackChanged() {
//     Log.i("teste"," tesatando "+ getFragmentManager().getBackStackEntryCount());
       if(getFragmentManager()!=null)mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
    }

    @Override
    public int returnBackStackCount(){
        return getFragmentManager().getBackStackEntryCount();
    }


    @Override
    public void onNeedChangePag(int position) {
        flipCard(position);
    }

    private void flipCard(int position){
        if (mShowingBack) {
            Log.i("teste"," tesatando flip");
            if(getFragmentManager()!=null)getFragmentManager().popBackStack();
            return;
        }

        mShowingBack = true;

        getActivity().getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)
                .replace(R.id.container, PagCardapioFrag.newInstance(
                        cardapio.get(position).getItens(), callBack.requestComanda()))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        callBack = (OnRequestComanda) activity;
    }
}
