package com.alexandrelunkes.modelolanche.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.alexandrelunkes.modelolanche.Itens.BotaoCardapio;
import com.alexandrelunkes.modelolanche.R;

/**
 * Created by Alexandre Lunkes on 06/10/2016.
 */
public class MensagensDialog extends DialogFragment{

    private TextView text;
    private Button bt;

    public static MensagensDialog newInstance(String duvida) {

        Bundle args = new Bundle();
        args.putString("duvida",duvida);
        MensagensDialog fragment = new MensagensDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        String duvida = getArguments().getString("duvida");

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_mensagens_layout,null);

        text = (TextView) v.findViewById(R.id.mensagem);
        bt = (Button) v.findViewById(R.id.botao_ok);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        text.setText(duvida);
        builder.setView(v);

        return builder.create();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(10,0,0,0)));
        getDialog().getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
        WindowManager.LayoutParams p = getDialog().getWindow().getAttributes();
        p.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE;

        getDialog().getWindow().setAttributes(p);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


}
