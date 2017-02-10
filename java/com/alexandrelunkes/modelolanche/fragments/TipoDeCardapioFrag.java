package com.alexandrelunkes.modelolanche.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alexandrelunkes.modelolanche.R;
import com.alexandrelunkes.modelolanche.adapters.ListTiposAdapter;
import com.alexandrelunkes.modelolanche.interfaces.OnNeedChangePagListener;

import java.util.List;

/**
 * Created by Alexandre Lunkes on 01/10/2016.
 */
public class TipoDeCardapioFrag extends Fragment implements AdapterView.OnItemClickListener{

    private ListView lista;
    private String[] textos;
    private Integer[] idsImagem;
    private OnNeedChangePagListener callBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_tipo_cardapio, null);

        lista = (ListView) v.findViewById(R.id.tipos);
        lista.setOnItemClickListener(this);

        setListView();


        return v;
    }

    private void setListView() {


        ListTiposAdapter adapter = new ListTiposAdapter(textos,idsImagem,getActivity());
        lista.setAdapter(adapter);


    }

    public void setNeedChangePagListener(OnNeedChangePagListener callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
           callBack.onNeedChangePag(position);
    }

    public void setValues(List<String> nomes, List<Integer> ids){

        textos = nomes.toArray(new String[nomes.size()]);
        idsImagem = ids.toArray(new Integer[ids.size()]);

    }
}
