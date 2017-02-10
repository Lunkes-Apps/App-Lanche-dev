package com.alexandrelunkes.modelolanche.Itens;

import java.io.Serializable;

/**
 * Created by Alexandre Lunkes on 28/09/2016.
 */
public class MesaItem implements Serializable{

    public static final int LIVRE = 1;
    public static final int OCUPADO = 0;



    private int numeroMesa;
    private boolean statusLivre;

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public boolean isStatusLivre() {
        return statusLivre;
    }

    public void setStatusLivre(boolean statusLivre) {
        this.statusLivre = statusLivre;
    }


}
