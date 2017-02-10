package com.alexandrelunkes.modelolanche.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.alexandrelunkes.modelolanche.InicioActivity;
import com.alexandrelunkes.modelolanche.Itens.CardapioItem;
import com.alexandrelunkes.modelolanche.Itens.Comanda;
import com.alexandrelunkes.modelolanche.R;
import com.alexandrelunkes.modelolanche.adapters.ListaCardapioAdapter;
import com.alexandrelunkes.modelolanche.interfaces.OnSelectItemListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre Lunkes on 28/09/2016.
 */
public class PagCardapioFrag extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private int tipo;
    private Spinner spinner;
    private String itens[];
    private ListView lista;
    private List<CardapioItem> cardapioList;
    private ArrayAdapter<CharSequence> adapter;
    private ListaCardapioAdapter adapterCardapio;
    private OnSelectItemListener callBack;
    private Comanda comanda;


    public static PagCardapioFrag newInstance(List<CardapioItem> itens, Comanda comanda) {

        Bundle args = new Bundle();

        PagCardapioFrag fragment = new PagCardapioFrag();
        String item = "item";
        for(int i=0; i<itens.size(); i++){
            args.putSerializable(item + Integer.toString(i),itens.get(i));
        }
        args.putSerializable("comanda", comanda);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_pagina_cardapio_frag, null);

        comanda = (Comanda) getArguments().getSerializable("comanda");

        cardapioList = new ArrayList<>();

        String item = "item";
        int i =0;
        CardapioItem aux;
        do{
            aux = (CardapioItem) getArguments().getSerializable(item+Integer.toString(i));
            if(aux!=null)cardapioList.add(aux);
            i++;
        }while (aux!=null);

        lista = (ListView) v.findViewById(R.id.list_itens);
        lista.setOnItemClickListener(this);

        setUpLista();

        if(comanda.getItens()!=null){
            atualizarComanda(comanda);
        }

        return v;
    }

    private void atualizarComanda(Comanda comanda) {

        for(CardapioItem i: comanda.getItens()){
            for(CardapioItem ii: cardapioList){
                if(i.getNome().equals(ii.getNome())){
                    ii.setQty(i.getQty());
                }
            }
        }

    }

    private void setUpLista() {

        adapterCardapio = new ListaCardapioAdapter(cardapioList, getActivity(), this);
        lista.setAdapter(adapterCardapio);

        itens = getResources().getStringArray(R.array.bebidas);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onClick(View view) {

        int position = lista.getPositionForView((View) view.getParent());

        switch (view.getId()) {
            case R.id.mais:
                cardapioList.get(position).setQty(cardapioList.get(position).getQty() + 1);
                break;
            case R.id.menos:
                if (cardapioList.get(position).getQty() > 0)
                    cardapioList.get(position).setQty(cardapioList.get(position).getQty() - 1);
                break;
        }

        adapterCardapio.notifyDataSetChanged();

        callBack.onAdicionaItem(cardapioList.get(position));

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        callBack = (OnSelectItemListener) activity;

    }
}
