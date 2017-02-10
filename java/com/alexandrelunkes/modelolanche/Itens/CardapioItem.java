package com.alexandrelunkes.modelolanche.Itens;

import android.media.Image;
import android.view.View;

import com.alexandrelunkes.modelolanche.fragments.PagCardapioFrag;

import java.io.Serializable;

/**
 * Created by Alexandre Lunkes on 29/09/2016.
 */
public class CardapioItem implements Serializable{

    private int idImage;
    private int idProduto;
    private String preco;
    private String nome;
    private int qty = 0;
    private View.OnClickListener callBack;

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public View.OnClickListener getCallBack() {
        return callBack;
    }

    public void setCallBack(View.OnClickListener callBack) {
        this.callBack = callBack;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



}
