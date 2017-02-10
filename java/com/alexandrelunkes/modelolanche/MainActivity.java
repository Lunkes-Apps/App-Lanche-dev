package com.alexandrelunkes.modelolanche;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.alexandrelunkes.modelolanche.Itens.CardapioItem;
import com.alexandrelunkes.modelolanche.Itens.Comanda;
import com.alexandrelunkes.modelolanche.adapters.ListMenuSliderItensAdapter;

import java.text.DecimalFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Comanda comanda;
    private TextView tvMesa;
    private TextView tvTotal;
    private ListView lvItens;
    private ListMenuSliderItensAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(this,InicioActivity.class);
        startActivityForResult(i,InicioActivity.RESULT_INICIO);

        tvMesa = (TextView) findViewById(R.id.mesa);
        tvTotal = (TextView) findViewById(R.id.total);
        lvItens = (ListView) findViewById(R.id.itens);

    }

    private void atualizarDados(){

        if(comanda !=null){
            Log.i("teste","comanda =" + comanda.getNumeroMesa());
            tvMesa.setText(getResources().getString(R.string.mesa)+": "+comanda.getNumeroMesa());
            float totalF = comanda.getValorTotal();
            DecimalFormat format = new DecimalFormat("0.00");
            tvTotal.setText("Total: R$ " + format.format(totalF));
            List<CardapioItem> itens = comanda.getItens();
            adapter = new ListMenuSliderItensAdapter(itens, this);
            lvItens.setAdapter(adapter);

        }else {
            Log.i("teste","comanda = null");
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("teste","comanda = entroi aqui");

        switch (requestCode){
            case InicioActivity.RESULT_INICIO:
                comanda = (Comanda) data.getExtras().getSerializable(InicioActivity.COMANDA);
                Log.i("teste","comanda =" + comanda.getNumeroMesa());
                atualizarDados();
                break;
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}
