package com.letsgo.appletsgo.app.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.utils.ScreenUtils;
import com.letsgo.appletsgo.domain.model.entity.Prices;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by louislopez on 6/03/17.
 */

public class PreciosComponent extends LinearLayout {
    private List<Prices> pricesList;
    private List<View> childrens;
    private Context context;

    public PreciosComponent(Context context) {
        super(context);
    }

    public PreciosComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PreciosComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(List<Prices> pricesList){
        this.pricesList = pricesList;
        this.context = context;
        populate();
    }

    private void populate(){
        int bottom= ScreenUtils.dpToPx(10);
        childrens= new ArrayList<>();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                (FrameLayout.LayoutParams.MATCH_PARENT), (FrameLayout.LayoutParams.WRAP_CONTENT));
        lp.setMargins(0, 0, 0, bottom);
        View child;
        Prices addresses;
        for (int i = 0 ; i < this.pricesList.size() ; i++){
            addresses = this.pricesList.get(i);
            child= buildCombosChild(addresses);
            //child.setLayoutParams(lp);
            this.addView(child);
            childrens.add(child);
        }
        this.requestLayout();
    }

    private View buildCombosChild(final Prices prices){
        View item = LayoutInflater.from(getContext()).inflate(R.layout.layout_precios,null);
        item.setTag(prices);

        final ViewHolder holder = new ViewHolder(item);

        holder.tviNamePrice.setText(prices.getType());
        if (prices.getPrice().equals("0.00")) {

            holder.tviPrice.setVisibility(GONE);
            holder.tviNamePrice.setVisibility(GONE);
        }
        else
            holder.tviPrice.setText("S/ " + prices.getPrice());
        return item;
    }

    private class ViewHolder {
        public TextView tviNamePrice;
        public TextView tviPrice;


        public ViewHolder(View container) {
            this.tviNamePrice = (TextView)container.findViewById(R.id.tviNamePrice);
            this.tviPrice = (TextView)container.findViewById(R.id.tviPrice);
        }
    }

}
