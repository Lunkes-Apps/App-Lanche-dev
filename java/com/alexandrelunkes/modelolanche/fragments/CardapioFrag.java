package com.alexandrelunkes.modelolanche.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexandrelunkes.modelolanche.Itens.BotaoCardapio;
import com.alexandrelunkes.modelolanche.Itens.CardapioItem;
import com.alexandrelunkes.modelolanche.Itens.ItemTipoCardapio;
import com.alexandrelunkes.modelolanche.R;
import com.alexandrelunkes.modelolanche.interfaces.OnRequestComanda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre Lunkes on 28/09/2016.
 */
public class CardapioFrag extends Fragment implements View.OnClickListener {

    private BotaoCardapio btComidas;
    private BotaoCardapio btBebidas;
    private OnRequestComanda callBack;
    private List<ItemTipoCardapio> comidas;
    private List<ItemTipoCardapio> bebidas;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_cardapio_layout, null);

        btComidas = (BotaoCardapio) v.findViewById(R.id.botao_comida);
        btBebidas = (BotaoCardapio) v.findViewById(R.id.botao_bebidas);

        btComidas.setOnClickListener(this);
        btBebidas.setOnClickListener(this);

        setCardapio();

        getFragmentManager().beginTransaction()
                .replace(R.id.frame_cardapio,
                        RotateCardapioFrag.newInstance(comidas))
                .commit();

        return v;
    }

    @Override
    public void onClick(View view) {

        Log.i("testando", " back " + getFragmentManager().getBackStackEntryCount());

        if (getFragmentManager().getBackStackEntryCount() != 0) {
            if (getFragmentManager()!=null)getFragmentManager().popBackStack();
        }
            switch (view.getId()) {

                case R.id.botao_comida:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_cardapio,
                                    RotateCardapioFrag.newInstance(comidas))
                            .commit();
                    break;
                case R.id.botao_bebidas:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_cardapio,
                                    RotateCardapioFrag.newInstance(bebidas))
                            .commit();
                    break;

            }


    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        callBack = (OnRequestComanda) activity;

    }

    private void setCardapio() {

        comidas = new ArrayList<>();
        bebidas = new ArrayList<>();

        //Simuacao comidas

        CardapioItem c1 = new CardapioItem();
        c1.setNome("X-Salada");
        c1.setPreco("3.50");

        CardapioItem c2 = new CardapioItem();
        c2.setNome("X-Bacon");
        c2.setPreco("5.50");

        CardapioItem c3 = new CardapioItem();
        c3.setNome("X-Tudo");
        c3.setPreco("13.50");

        CardapioItem c4 = new CardapioItem();
        c4.setNome("Hot-Dog");
        c4.setPreco("3.50");

        CardapioItem c5 = new CardapioItem();
        c5.setNome("Misto-Quente");
        c5.setPreco("2.50");

        CardapioItem c6 = new CardapioItem();
        c6.setNome("Baguet");
        c6.setPreco("6.90");

        ItemTipoCardapio sanduiches = new ItemTipoCardapio();
        sanduiches.setNome("Sanduiches");
        sanduiches.setIdImage(R.drawable.hamburger_1000_400);
        List<CardapioItem> items1 = new ArrayList<>();

        items1.add(c1);
        items1.add(c2);
        items1.add(c3);
        items1.add(c4);
        items1.add(c5);
        items1.add(c6);

        sanduiches.setItens(items1);

        CardapioItem c11 = new CardapioItem();
        c11.setNome("Portuguesa");
        c11.setPreco("13.50");

        CardapioItem c12 = new CardapioItem();
        c12.setNome("Calabresa");
        c12.setPreco("15.50");

        CardapioItem c13 = new CardapioItem();
        c13.setNome("Catupiry");
        c13.setPreco("13.50");

        CardapioItem c14 = new CardapioItem();
        c14.setNome("Quatro Queijos");
        c14.setPreco("13.50");

        CardapioItem c15 = new CardapioItem();
        c15.setNome("Vegetariana");
        c15.setPreco("22.50");

        CardapioItem c16 = new CardapioItem();
        c16.setNome("Moda da Casa");
        c16.setPreco("16.90");

        ItemTipoCardapio pizzas = new ItemTipoCardapio();
        pizzas.setNome("Pizzas");
        pizzas.setIdImage(R.drawable.pizza_1000_400);
        List<CardapioItem> items11 = new ArrayList<>();

        items11.add(c11);
        items11.add(c12);
        items11.add(c13);
        items11.add(c14);
        items11.add(c15);
        items11.add(c16);

        pizzas.setItens(items11);

        CardapioItem c21 = new CardapioItem();
        c21.setNome("Peito de Frango");
        c21.setPreco("31.50");

        CardapioItem c22 = new CardapioItem();
        c22.setNome("Costela Bovina");
        c22.setPreco("51.50");

        CardapioItem c23 = new CardapioItem();
        c23.setNome("Stake Bovino");
        c23.setPreco("13.50");

        CardapioItem c24 = new CardapioItem();
        c24.setNome("Picanha");
        c24.setPreco("33.50");

        CardapioItem c25 = new CardapioItem();
        c25.setNome("Frango Milanesa");
        c25.setPreco("23.50");

        CardapioItem c26 = new CardapioItem();
        c26.setNome("Camarão Frito");
        c26.setPreco("61.90");

        ItemTipoCardapio grelhados = new ItemTipoCardapio();
        grelhados.setNome("Grelhados");
        grelhados.setIdImage(R.drawable.grelhados_1000_400);
        List<CardapioItem> items21 = new ArrayList<>();

        items21.add(c21);
        items21.add(c22);
        items21.add(c23);
        items21.add(c24);
        items21.add(c25);
        items21.add(c26);

        grelhados.setItens(items21);

        CardapioItem c31 = new CardapioItem();
        c31.setNome("Frios de Queijo");
        c31.setPreco("13.50");

        CardapioItem c32 = new CardapioItem();
        c32.setNome("Presunto e Queijo");
        c32.setPreco("15.50");

        CardapioItem c33 = new CardapioItem();
        c33.setNome("Batata Frita");
        c33.setPreco("13.50");

        CardapioItem c34 = new CardapioItem();
        c34.setNome("Esfirras");
        c34.setPreco("3.50");

        CardapioItem c35 = new CardapioItem();
        c35.setNome("Quibes");
        c35.setPreco("4.50");

        CardapioItem c36 = new CardapioItem();
        c36.setNome("Espetinhos");
        c36.setPreco("6.90");

        ItemTipoCardapio petiscos = new ItemTipoCardapio();
        petiscos.setNome("Petiscos");
        petiscos.setIdImage(R.drawable.petiscos_1000_400);
        List<CardapioItem> items31 = new ArrayList<>();

        items31.add(c31);
        items31.add(c32);
        items31.add(c33);
        items31.add(c34);
        items31.add(c35);
        items31.add(c36);

        petiscos.setItens(items31);


        comidas.add(sanduiches);
        comidas.add(pizzas);
        comidas.add(grelhados);
        comidas.add(petiscos);

        //Bebidas simulacao

        CardapioItem b1 = new CardapioItem();
        b1.setNome("Laranja");
        b1.setPreco("5.50");

        CardapioItem b2 = new CardapioItem();
        b2.setNome("Acerola");
        b2.setPreco("5.50");

        CardapioItem b3 = new CardapioItem();
        b3.setNome("Maracujá");
        b3.setPreco("5.50");

        CardapioItem b4 = new CardapioItem();
        b4.setNome("Goiaba");
        b4.setPreco("5.50");

        CardapioItem b5 = new CardapioItem();
        b5.setNome("Uva");
        b5.setPreco("5.50");

        CardapioItem b6 = new CardapioItem();
        b6.setNome("Genipapo");
        b6.setPreco("5.90");

        ItemTipoCardapio sucos = new ItemTipoCardapio();
        sucos.setNome("Sucos");
        sucos.setIdImage(R.drawable.suco_1000_400);
        List<CardapioItem> itemsb1 = new ArrayList<>();

        itemsb1.add(b1);
        itemsb1.add(b2);
        itemsb1.add(b3);
        itemsb1.add(b4);
        itemsb1.add(b5);
        itemsb1.add(b6);

        sucos.setItens(itemsb1);

        CardapioItem b11 = new CardapioItem();
        b11.setNome("Coca Cola");
        b11.setPreco("5.50");

        CardapioItem b12 = new CardapioItem();
        b12.setNome("Fanta Laranja");
        b12.setPreco("5.50");

        CardapioItem b13 = new CardapioItem();
        b13.setNome("Fanta Uva");
        b13.setPreco("5.50");

        CardapioItem b14 = new CardapioItem();
        b14.setNome("Bare");
        b14.setPreco("3.50");

        CardapioItem b15 = new CardapioItem();
        b15.setNome("Guarana Antartica");
        b15.setPreco("5.50");

        CardapioItem b16 = new CardapioItem();
        b16.setNome("Regente");
        b16.setPreco("5.90");

        ItemTipoCardapio refirgerantes = new ItemTipoCardapio();
        refirgerantes.setNome("Gefrigerantes");
        refirgerantes.setIdImage(R.drawable.refirgerantes_1000_400);
        List<CardapioItem> itemsb11 = new ArrayList<>();

        itemsb11.add(b11);
        itemsb11.add(b12);
        itemsb11.add(b13);
        itemsb11.add(b14);
        itemsb11.add(b15);
        itemsb11.add(b16);

        refirgerantes.setItens(itemsb11);

        CardapioItem b21 = new CardapioItem();
        b21.setNome("Caipirinha");
        b21.setPreco("31.50");

        CardapioItem b22 = new CardapioItem();
        b22.setNome("Vodika");
        b22.setPreco("51.50");

        CardapioItem b23 = new CardapioItem();
        b23.setNome("Vinho");
        b23.setPreco("13.50");

        CardapioItem b24 = new CardapioItem();
        b24.setNome("Espumantes");
        b24.setPreco("33.50");

        CardapioItem b25 = new CardapioItem();
        b25.setNome("Pinga Mesmo");
        b25.setPreco("23.50");

        CardapioItem b26 = new CardapioItem();
        b26.setNome("Cerveja");
        b26.setPreco("61.90");

        ItemTipoCardapio drink = new ItemTipoCardapio();
        drink.setNome("Drinks");
        drink.setIdImage(R.drawable.drinks_1000_400);
        List<CardapioItem> itemsb21 = new ArrayList<>();

        itemsb21.add(b21);
        itemsb21.add(b22);
        itemsb21.add(b23);
        itemsb21.add(b24);
        itemsb21.add(b25);
        itemsb21.add(b26);

        drink.setItens(itemsb21);

        bebidas.add(sucos);
        bebidas.add(refirgerantes);
        bebidas.add(drink);

    }


}
