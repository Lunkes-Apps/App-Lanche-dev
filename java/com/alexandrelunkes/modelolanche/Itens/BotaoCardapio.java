package com.alexandrelunkes.modelolanche.Itens;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.alexandrelunkes.modelolanche.R;

/**
 * Created by Alexandre Lunkes on 29/09/2016.
 */
public class BotaoCardapio extends TextView{

    private AnimationDrawable animationDrawable;

    public BotaoCardapio(Context context) {
        this(context,null);
    }

    public BotaoCardapio(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public BotaoCardapio(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        setBackgroundResource(R.drawable.animation_button);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case 1:
                animationDrawable = (AnimationDrawable) this.getBackground();
                animationDrawable.stop();
                animationDrawable.start();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("teste", "onTouch");
                break;
            default:break;

        }

        return super.onTouchEvent(event);
    }
}
