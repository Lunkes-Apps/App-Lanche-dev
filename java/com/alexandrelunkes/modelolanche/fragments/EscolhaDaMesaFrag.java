package com.alexandrelunkes.modelolanche.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alexandrelunkes.modelolanche.InicioActivity;
import com.alexandrelunkes.modelolanche.Itens.MesaItem;
import com.alexandrelunkes.modelolanche.R;
import com.alexandrelunkes.modelolanche.adapters.ListaMesasAdapter;
import com.alexandrelunkes.modelolanche.interfaces.OnSelectItemListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre Lunkes on 28/09/2016.
 */
public class EscolhaDaMesaFrag extends Fragment implements AdapterView.OnItemClickListener{

    private List<MesaItem> mesas;
    private ListView listaMesas;
    private OnSelectItemListener callBack;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_escolha_mesa_layout, null);

        listaMesas = (ListView) v.findViewById(R.id.list_mesas);
        listaMesas.setOnItemClickListener(this);

        setUpMesas();

        return v;
    }

    private void setUpMesas() {

        mesas =new ArrayList<>();

        MesaItem m1 = new MesaItem();
        MesaItem m2 = new MesaItem();
        MesaItem m3 = new MesaItem();
        MesaItem m4 = new MesaItem();
        MesaItem m5 = new MesaItem();

        m1.setNumeroMesa(1);
        m1.setStatusLivre(false);

        m2.setNumeroMesa(2);
        m2.setStatusLivre(true);

        m3.setNumeroMesa(3);
        m3.setStatusLivre(true);

        m4.setNumeroMesa(4);
        m4.setStatusLivre(false);

        m5.setNumeroMesa(5);
        m5.setStatusLivre(true);



        mesas.add(m1);
        mesas.add(m2);
        mesas.add(m3);
        mesas.add(m4);
        mesas.add(m5);

        ListaMesasAdapter adapter = new ListaMesasAdapter(mesas,getActivity());

        listaMesas.setAdapter(adapter);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        callBack = (OnSelectItemListener) activity;

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        callBack.onSelectedItem(i, InicioActivity.ESCOLHA_DA_MESA,view);
    }
}
