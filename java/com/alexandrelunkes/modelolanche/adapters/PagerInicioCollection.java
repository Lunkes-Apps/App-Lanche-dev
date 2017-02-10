package com.alexandrelunkes.modelolanche.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;


import java.util.List;

/**
 * Created by Alexandre Lunkes on 28/09/2016.
 */
public class PagerInicioCollection extends FragmentStatePagerAdapter{

    List<Fragment> paginas;

    public PagerInicioCollection(FragmentManager fm, List<Fragment> paginas) {
        super(fm);
        this.paginas = paginas;
    }

    @Override
    public Fragment getItem(int position) {
        return paginas.get(position);
    }

    @Override
    public int getCount() {
        return paginas.size();
    }
}
