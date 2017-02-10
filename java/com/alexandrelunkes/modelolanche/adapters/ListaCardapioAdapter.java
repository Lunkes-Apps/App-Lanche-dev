package com.alexandrelunkes.modelolanche.adapters;

import android.content.Context;
import android.content.Intent;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alexandrelunkes.modelolanche.Itens.BotaoCardapio;
import com.alexandrelunkes.modelolanche.Itens.CardapioItem;
import com.alexandrelunkes.modelolanche.R;

import java.util.List;

/**
 * Created by Alexandre Lunkes on 29/09/2016.
 */
public class ListaCardapioAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private List<CardapioItem> lista;
    private OnAtualizaQtyListener callBack;
    private int p;
    private Fragment fragment;

    public ListaCardapioAdapter(List<CardapioItem> lista, Context context, Fragment fragment) {

//        try {
//            callBack = (OnAtualizaQtyListener) fragment;
//        }catch (ClassCastException e){
//            Log.e("ModeloLanche", e.getMessage()+" Precisa implementar interface");
//        }

        this.fragment = fragment;

        this.lista = lista;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return lista.size();
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
    public View getView(int position, View view, ViewGroup viewGroup) {

        final ItemSuport holder;

        if (view == null) {
            holder = new ItemSuport();

            view = inflater.inflate(R.layout.adapter_list_cardapio_layout, null);
            holder.nome = (TextView) view.findViewById(R.id.nome);
            holder.preco = (TextView) view.findViewById(R.id.preco);
            holder.qty = (TextView) view.findViewById(R.id.qty);
            holder.mais = (BotaoCardapio) view.findViewById(R.id.mais);
            holder.menos = (BotaoCardapio) view.findViewById(R.id.menos);

            view.setTag(holder);
        } else {
            holder = (ItemSuport) view.getTag();
        }


        holder.nome.setText(lista.get(position).getNome());
        holder.preco.setText("R$ " + lista.get(position).getPreco());
        holder.qty.setText(Integer.toString(lista.get(position).getQty()));
        holder.mais.setOnClickListener((View.OnClickListener) fragment);
        holder.menos.setOnClickListener((View.OnClickListener) fragment);
        return view;
    }



    public class ItemSuport {

        private TextView nome;
        private TextView preco;
        private TextView qty;
        private BotaoCardapio mais;


        public BotaoCardapio getMenos() {
            return menos;
        }

        public void setMenos(BotaoCardapio menos) {
            this.menos = menos;
        }

        public BotaoCardapio getMais() {
            return mais;
        }

        public void setMais(BotaoCardapio mais) {
            this.mais = mais;
        }

        private BotaoCardapio menos;

        public TextView getQty() {
            return qty;
        }

        public void setQty(TextView qty) {
            this.qty = qty;
        }

        public TextView getNome() {
            return nome;
        }

        public void setNome(TextView nome) {
            this.nome = nome;
        }

        public TextView getPreco() {
            return preco;
        }

        public void setPreco(TextView preco) {
            this.preco = preco;
        }


    }


    public interface OnAtualizaQtyListener {

        void onAtualiza(int position, int tipo);

    }

}
