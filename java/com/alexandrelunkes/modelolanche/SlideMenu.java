package com.alexandrelunkes.modelolanche;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alexandrelunkes.modelolanche.Itens.BotaoCardapio;
import com.alexandrelunkes.modelolanche.Itens.CardapioItem;
import com.alexandrelunkes.modelolanche.Itens.Comanda;
import com.alexandrelunkes.modelolanche.adapters.ListMenuSliderItensAdapter;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.LogManager;

/**
 * Created by Alexandre Lunkes on 30/09/2016.
 */

public class    SlideMenu extends LinearLayout implements View.OnClickListener {


    private final int PADDING_TEXT = (int) (getResources().getDisplayMetrics().density * 5);
    private final int MARGIN_TEXT = 10;
    private static final int idBtFim = generateViewId();
    private static final int idTotal = generateViewId();
    private static final int idValor = generateViewId();


    private int heightSize;
    private int maxHeight;
    private Comanda comanda;
    private Context context;
    private TextView total;
    private TextView valorFloatTx;
    private TextView mesa;
    private LinearLayout linear;
    private LinearLayout horizontal;
    private ListView listItens;
    private boolean isMenuUp = false;
    private ListMenuSliderItensAdapter adapter;
    private float totalF = 0;
    private DecimalFormat format;
    private BotaoCardapio btFim;
    private OnSlideMenuListener onSlideMenuListener;


    public SlideMenu(Context context) {
        this(context, null);
    }

    public SlideMenu(final Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        try {
            onSlideMenuListener = (OnSlideMenuListener) context;

        } catch (ClassCastException e) {
            Log.e("ModeloLanche", "Activity precisa implementar OnSlideMenuListener " + e.getMessage());
        }


        maxHeight = getResources().getDisplayMetrics().heightPixels / 2;
        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams layoutH = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        linear = new LinearLayout(context);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setLayoutParams(layout);
        listItens = new ListView(context);
        listItens.setLayoutParams(layout);

        LinearLayout.LayoutParams layoutTexts = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutTexts.setMargins(MARGIN_TEXT, MARGIN_TEXT, MARGIN_TEXT, MARGIN_TEXT);

        format = new DecimalFormat("0.00");

        horizontal = new LinearLayout(context);
        horizontal = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.horizontal_menu_total_layout, null);
        total = (TextView) horizontal.findViewById(R.id.total);
        valorFloatTx = (TextView) horizontal.findViewById(R.id.valor);
        btFim = (BotaoCardapio) horizontal.findViewById(R.id.botao);
        btFim.setOnClickListener(this);

        horizontal.setLayoutParams(layoutH);
        linear.addView(horizontal);

        linear.setOnClickListener(this);
        linear.setBackgroundColor(ContextCompat.getColor(context, R.color.slideMenu));

        mesa = new TextView(context);
        mesa.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        mesa.setTextColor(ContextCompat.getColor(context,R.color.colorText));
        mesa.setLayoutParams(layoutTexts);

        addView(linear);
    }

    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.botao:
                onSlideMenuListener.pedir();
                break;
            default:
                slideMenu();
                break;
        }

    }

    private void slideMenu() {
        if (comanda != null) {

            List<CardapioItem> itens = comanda.getItens();
            adapter = new ListMenuSliderItensAdapter(itens, context);

            if (isMenuUp) {

                linear.setVisibility(INVISIBLE);

                listItens.setAdapter(null);
                linear.removeView(listItens);
                linear.removeView(mesa);

                linear.setVisibility(VISIBLE);

                isMenuUp = false;

            } else {

                if (itens.size() > 0) {
                    linear.setVisibility(INVISIBLE);

                    listItens.setAdapter(adapter);
                    linear.addView(mesa);
                    linear.addView(listItens);
                    linear.setVisibility(VISIBLE);
                    isMenuUp = true;
                }
            }

        }

    }

    public void atualizarComanda(Comanda comanda) {
        this.comanda = comanda;

        mesa.setText("Mesa: "+comanda.getNumeroMesa());

        if (comanda.getItens().size() == 0) {
            isMenuUp = false;
            listItens.setAdapter(null);
        }
        if (adapter != null)
            adapter.notifyDataSetChanged();

        totalF = comanda.getValorTotal();
        valorFloatTx.setText("R$ " + format.format(totalF));
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        try {
            heightSize = MeasureSpec.getSize(heightMeasureSpec);
            if (maxHeight != -1
                    && heightSize > maxHeight) {
                heightSize = maxHeight;
            }

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.AT_MOST);
            getLayoutParams().height = heightSize;
        } catch (Exception e) {
            //LogManager.error(this, "onMesure", "Error forcing height", e);
        } finally {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }

    /**
     * Generate a value suitable for use in {@link #setId(int)}.
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    public static int generateViewId() {
        final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    public interface OnSlideMenuListener {
        void pedir();
    }


}
