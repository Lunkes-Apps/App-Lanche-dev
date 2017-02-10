package com.alexandrelunkes.modelolanche;

import android.app.Fragment;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alexandrelunkes.modelolanche.Itens.CardapioItem;
import com.alexandrelunkes.modelolanche.Itens.Comanda;
import com.alexandrelunkes.modelolanche.Itens.ViewPagerCustom;
import com.alexandrelunkes.modelolanche.adapters.ListaMesasAdapter;
import com.alexandrelunkes.modelolanche.adapters.PagerInicioCollection;
import com.alexandrelunkes.modelolanche.dialogs.MensagensDialog;
import com.alexandrelunkes.modelolanche.fragments.CardapioFrag;
import com.alexandrelunkes.modelolanche.fragments.EscolhaDaMesaFrag;
import com.alexandrelunkes.modelolanche.interfaces.OnRequestComanda;
import com.alexandrelunkes.modelolanche.interfaces.OnSelectItemListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

public class InicioActivity extends AppCompatActivity implements OnSelectItemListener,View.OnClickListener,
        OnRequestComanda, SlideMenu.OnSlideMenuListener{

    public static final int ESCOLHA_DA_MESA = 1;
    public static final String COMANDA = "comanda_resultado_do_inicio";
    public static final int RESULT_INICIO = 103;

    public static final int ATUALIZA_LISTA = 2;
//    public static final int ESCOLHA_DA_MESA = 1;
//    public static final int ESCOLHA_DA_MESA = 1;

    boolean down = false;

    private String toast;
    private SlideMenu slideMenu;
    private ViewPagerCustom pager;
    private List<Fragment> paginas;
    private PagerInicioCollection collection;
    private Comanda comanda;
    private TextView textoTotal;
    private FrameLayout totalFrame;
    private ListaMesasAdapter.ItemSuport itemMesa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        pager = (ViewPagerCustom) findViewById(R.id.pager_inicio);
        pager.setSwipeEnabled(false);
        comanda = new Comanda();
        slideMenu = (SlideMenu) findViewById(R.id.slide);


//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.frame_total,new ResumoFragment()).commit();
//
//        totalFrame = (FrameLayout) findViewById(R.id.frame_total);
//        totalFrame.setOnClickListener(this);





        setUpPager();

    }

    private Handler myHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


        }
    };

    private void setUpPager() {
        EscolhaDaMesaFrag escolhaDaMesa = new EscolhaDaMesaFrag();
        CardapioFrag cardapioFrag = new CardapioFrag();

        paginas = new ArrayList<>();

        paginas.add(escolhaDaMesa);
        paginas.add(cardapioFrag);

        collection = new PagerInicioCollection(getFragmentManager(),paginas);

        pager.setAdapter(collection);


    }

    private void scanCodigo(){
        new IntentIntegrator(this).initiateScan();
    }


    @Override
    public void onSelectedItem(int position, int tipo, View view) {


        switch (tipo){
            case ESCOLHA_DA_MESA:

                itemMesa = (ListaMesasAdapter.ItemSuport) view.getTag();
                if(!itemMesa.getStatus().getText().toString().equals(getResources().getString(R.string.ocupado))) {

                    scanCodigo();


                }else {
                    MensagensDialog mensagensDialog = MensagensDialog.newInstance(
                            getResources().getString(R.string.mensagem_mesa_ocupada)
                    );
                    mensagensDialog.show(getFragmentManager().beginTransaction(),"dialog");
                }
                break;

        }

        Log.i("Teste"," position "+position);
    }

    @Override
    public void onAdicionaItem(CardapioItem item) {
        comanda.atualizaLista(item);
        slideMenu.atualizarComanda(comanda);
    }

    @Override
    public void onClick(View view) {

        float origem = totalFrame.getY();

        if(down){
//            Animation down = AnimationUtils.loadAnimation(this,R.anim.total_animation_view_up);
//            totalFrame.startAnimation(down);

            totalFrame.setY(0);

            this.down = false;
        }else{
//            Animation down = AnimationUtils.loadAnimation(this,R.anim.total_animation_view_down);
//            totalFrame.startAnimation(down);



            this.down = true;
        }





        Log.i("Teste_anim","animou");
    }

    @Override
    public Comanda requestComanda() {
        return comanda;
    }

    @Override
    public void pedir() {
        Log.i("Teste", " pedir mesa " + comanda.getNumeroMesa());
        Intent i = new Intent();
        i.putExtra(COMANDA,comanda);
        setResult(RESULT_INICIO,i);

        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null) {
            if(result.getContents() == null) {

                Toast.makeText(this,"Escolha a mesa e leia o código",Toast.LENGTH_LONG).show();

            } else {

                    comanda.setNumeroMesa(Integer.parseInt(itemMesa.getNumero().getText().toString()));
                    Log.i("Teste", " status " + comanda.getNumeroMesa());
                    pager.setCurrentItem(1);

                toast = "Mesa escolhida: " + itemMesa.getNumero();
                Toast.makeText(this,toast,Toast.LENGTH_LONG).show();
            }

            // At this point we may or may not have a reference to the activity

        }
    }
}
