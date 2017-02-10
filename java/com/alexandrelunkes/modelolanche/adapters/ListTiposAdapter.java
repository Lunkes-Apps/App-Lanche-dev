package com.alexandrelunkes.modelolanche.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexandrelunkes.modelolanche.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Alexandre Lunkes on 01/10/2016.
 */
public class ListTiposAdapter extends BaseAdapter{

    private String[] tipos;
    private Integer [] idImagens;
    private LayoutInflater inflater;

    public ListTiposAdapter (String[] tipos, Integer [] idImagens, Context context){

        this.tipos = tipos;
        this.idImagens = idImagens;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return tipos.length;
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

        if(view==null){
            view =inflater.inflate(R.layout.adapter_list_tipo_cardapio,null);
            holder = new ItemSuport();

            holder.image = (ImageView) view.findViewById(R.id.image);
            holder.text = (TextView) view.findViewById(R.id.text);
            view.setTag(holder);
        }else {
            holder = (ItemSuport) view.getTag();
        }

        holder.text.setText(tipos[i]);
        holder.image.setImageResource(idImagens[i].intValue());

        return view;
    }

    class ItemSuport{

        ImageView image;
        TextView text;

    }



}
