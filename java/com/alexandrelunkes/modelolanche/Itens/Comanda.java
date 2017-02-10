package com.alexandrelunkes.modelolanche.Itens;

import android.util.Log;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Alexandre Lunkes on 29/09/2016.
 */
public class Comanda extends MesaItem implements Serializable {

    private List<CardapioItem> itens;
    private float valorTotal;
    private Calendar data;
    private boolean isPago;
    private int idResponsavel;
    private LogginCliente loggin;

    public List<CardapioItem> getItens() {
        return itens;
    }

    public void setItens(List<CardapioItem> itens) {
        this.itens = itens;
    }

    public float getValorTotal() {

        valorTotal = 0;

        if(itens!=null){
            for(CardapioItem i: itens){
                valorTotal += (Float.parseFloat(i.getPreco())*i.getQty());
            }
        }

        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public boolean isPago() {
        return isPago;
    }

    public void setPago(boolean pago) {
        isPago = pago;
    }

    public int getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(int idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public void atualizaLista(CardapioItem cardapioItem) {

        if(itens == null){
            itens = new ArrayList<>();
            if(cardapioItem.getQty()>0)
            itens.add(cardapioItem);
        }else {
            for(CardapioItem c: itens){
                if(c.getNome().equals(cardapioItem.getNome())){
                    if(cardapioItem.getQty()==0){
                        Log.i("teste", " qty 0 deletar item");
                        itens.remove(c);
                    }else {
                        c.setQty(cardapioItem.getQty());
                    }
                    return ;
                }
            }
            if(cardapioItem.getQty()>0)
            itens.add(cardapioItem);
        }
    }


}
