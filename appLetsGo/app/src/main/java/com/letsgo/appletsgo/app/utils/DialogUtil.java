package com.letsgo.appletsgo.app.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.letsgo.appletsgo.R;

import java.util.List;

/**
 * Created by sergio on 24/12/16.
 *
 * Clase utilitaria de dialogos
 */
public class DialogUtil {

    private OnDialogListener onDialogListener;

    public DialogUtil(OnDialogListener onDialogListener){
        this.onDialogListener = onDialogListener;
    }

    public Dialog createCustomDialog(String title, String message, Context context, final int index, boolean buttonAccept, boolean buttonCancel){

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = View.inflate(context, R.layout.custom_dialog, null);

        TextView tviMessage = (TextView) view.findViewById(R.id.tviMessage);
        TextView tviAccept = (TextView) view.findViewById(R.id.tviAccept);
        TextView tviCancel = (TextView) view.findViewById(R.id.tviCancel);

        if(tviMessage == null)tviMessage.setVisibility(View.GONE);
        if(!buttonAccept){
            tviAccept.setVisibility(View.GONE);
        }
        if(!buttonCancel){
            tviCancel.setVisibility(View.GONE);
        }

        tviAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDialogListener.onAccept(index);
            }
        });

        tviCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDialogListener.onCancel(index);
            }
        });

        tviMessage.setText(message);

        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);

        return  dialog;
    }

    public interface OnDialogListener {

        void onAccept(int index);
        void onCancel(int index);
    }
}
