package com.alexandrelunkes.modelolanche.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alexandrelunkes.modelolanche.Itens.MesaItem;
import com.alexandrelunkes.modelolanche.R;

import java.util.List;

/**
 * Created by Alexandre Lunkes on 28/09/2016.
 */
public class ListaMesasAdapter extends BaseAdapter {

    private List<MesaItem> mesas;
    private LayoutInflater inflater;


    public ListaMesasAdapter(List<MesaItem> mesas, Context context) {
        this.mesas = mesas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mesas.size();
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

         ItemSuport holder;

        if(view == null){
            view = inflater.inflate(R.layout.adapter_list_mesa_layout,null);
            holder = new ItemSuport();
            holder.numero =(TextView) view.findViewById(R.id.text_numero);
            holder.status =(TextView) view.findViewById(R.id.text_status);

            view.setTag(holder);
        }else {
            holder =(ItemSuport) view.getTag();
        }

         holder.numero.setText(Integer.toString(mesas.get(position).getNumeroMesa()));
         if(mesas.get(position).isStatusLivre()){
             holder.status.setText(view.getContext().getResources().getText(R.string.livre));
         }else {
             holder.status.setText(view.getContext().getResources().getText(R.string.ocupado));
         }

        return view;
    }


   public class ItemSuport{

     private TextView numero;
     private TextView status;

       public TextView getNumero() {
           return numero;
       }

       public void setNumero(TextView numero) {
           this.numero = numero;
       }

       public TextView getStatus() {
           return status;
       }

       public void setStatus(TextView status) {
           this.status = status;
       }
   }





}
