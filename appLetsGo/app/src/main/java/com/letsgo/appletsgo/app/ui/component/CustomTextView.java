package com.letsgo.appletsgo.app.ui.component;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by jebus on 24/03/15.
 */
public class CustomTextView extends TextView {

    public CustomTextView(Context context) {
        super(context);
        init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
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
