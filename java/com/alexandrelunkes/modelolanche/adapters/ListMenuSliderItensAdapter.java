package com.alexandrelunkes.modelolanche.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alexandrelunkes.modelolanche.Itens.CardapioItem;
import com.alexandrelunkes.modelolanche.R;

import java.util.List;

/**
 * Created by Alexandre Lunkes on 30/09/2016.
 */
public class ListMenuSliderItensAdapter extends BaseAdapter {

    private List<CardapioItem> itens;
    private LayoutInflater inflater;

    public ListMenuSliderItensAdapter(List<CardapioItem> itens, Context context){
        this.itens = itens;
        inflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ItemSuport holder;

        if(view == null){
            holder = new ItemSuport();
            view = inflater.inflate(R.layout.adapter_list_menu,null);

            holder.nome = (TextView) view.findViewById(R.id.nome);
            holder.qty = (TextView) view.findViewById(R.id.qty);
            holder.valor = (TextView) view.findViewById(R.id.valor);

            view.setTag(holder);
        }else {
            holder = (ItemSuport) view.getTag();
        }

        holder.nome.setText(itens.get(i).getNome());
        holder.qty.setText(Integer.toString(itens.get(i).getQty()));
        holder.valor.setText(itens.get(i).getPreco());

        return view;
    }

    class ItemSuport{
        TextView nome;
        TextView qty;
        TextView valor;

    }




}
