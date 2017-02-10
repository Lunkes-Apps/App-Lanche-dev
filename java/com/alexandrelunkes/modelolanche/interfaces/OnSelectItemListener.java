package com.alexandrelunkes.modelolanche.interfaces;

import android.view.View;

import com.alexandrelunkes.modelolanche.Itens.CardapioItem;

/**
 * Created by Alexandre Lunkes on 29/09/2016.
 */
public interface OnSelectItemListener {
    void onSelectedItem(int position, int tipo, View view);
    void onAdicionaItem(CardapioItem item);
}
