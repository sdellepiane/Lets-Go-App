package com.letsgo.appletsgo.app.ui.component;

/**
 * Created by louislopez on 22/02/17.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;


/**
 * Created by jebus on 24/03/15.
 */
public class CustomButtonFont extends Button {

    public CustomButtonFont(Context context) {
        super(context);
        init(context);
    }

    public CustomButtonFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomButtonFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context){
        String font = "";
        try {
            switch (Integer.parseInt(String.valueOf(getTag()))){
                case 1: font = "fonts/Roboto-Light.ttf";break;
                case 2: font = "fonts/Roboto-Medium.ttf";break;
                case 3: font = "fonts/Roboto-Bold.ttf";break;
            }
        }catch (Exception e){
            font = "fonts/Roboto-Regular.ttf";
        }


        Typeface tf = Typeface.createFromAsset(context.getAssets(), font);
        setTypeface(tf);
    }
}
