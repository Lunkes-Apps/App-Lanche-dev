package com.alexandrelunkes.modelolanche.Itens;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Alexandre Lunkes on 04/10/2016.
 */
public class ItemTipoCardapio implements Serializable{

    private int idImage;
    private String nome;
    private List<CardapioItem> itens;

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<CardapioItem> getItens() {
        return itens;
    }

    public void setItens(List<CardapioItem> itens) {
        this.itens = itens;
    }
}
