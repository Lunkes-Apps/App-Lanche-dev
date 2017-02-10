package com.alexandrelunkes.modelolanche.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexandrelunkes.modelolanche.R;

/**
 * Created by Alexandre Lunkes on 29/09/2016.
 */
public class ResumoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_resumo,null);

        return v;
    }
}
