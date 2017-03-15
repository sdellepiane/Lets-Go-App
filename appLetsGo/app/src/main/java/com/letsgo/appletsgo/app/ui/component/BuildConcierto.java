package com.letsgo.appletsgo.app.ui.component;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.activity.FilterFirstActivity;
import com.letsgo.appletsgo.app.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by louislopez on 23/02/17.
 */

public class BuildConcierto {

    public View buildConcierto(int layout, Context context){
        View item = LayoutInflater.from(context).inflate(layout,null);
        final ViewHolder holder = new ViewHolder(item);

        if (holder.tviElectro != null) {
            holder.tviElectro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LogUtils.v("CLICK", "Elentro");
                }
            });
        }

        if (holder.tviComedia != null) {
            holder.tviComedia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LogUtils.v("CLICK", holder.tviComedia.getText().toString());
                }
            });
        }
        return item;
    }

    public class ViewHolder {
        @Nullable
        @BindView(R.id.tviElectro) TextView tviElectro;
        @Nullable @BindView(R.id.tviComedia) TextView tviComedia;
        public ViewHolder(View container) {
            ButterKnife.bind(this,container);
        }
    }
}
