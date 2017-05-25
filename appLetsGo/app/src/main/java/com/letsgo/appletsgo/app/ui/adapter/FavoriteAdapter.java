package com.letsgo.appletsgo.app.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.activity.DetailEvent2Activity;
import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.view.ActividadesView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by louislopez on 12/02/17.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private String TAG = "ADAPTER_ACTIVIDAD" ;
    private Context context;
    private List<Actividades> actividadesList = new ArrayList<>();
    private int mDay;
    private int month;
    private int day;
    private int mYear;
    private int year1;
    private int mMonth;

    public FavoriteAdapter(Context context, List<Actividades> actividadesList) {
        this.context  = context;
        this.actividadesList  = actividadesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_actividades,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Actividades actividades = actividadesList.get(position);

        holder.tviNameEvent.setText(actividadesList.get(position).getActivity());

        holder.tviCategory.setText(actividadesList.get(position).getType());

        if (actividadesList.get(position).getPrice().equals("0.00"))
            holder.tviPrice.setText("Gratis");
        else
            holder.tviPrice.setText(actividadesList.get(position).getPrice());

        holder.iviFavorite.setImageResource(R.drawable.ic_favorite_red);
        holder.iviClock.setColorFilter(context.getResources().getColor(R.color.colorRed));
        holder.iviTicket.setColorFilter(context.getResources().getColor(R.color.colorRed));

        Picasso.with(context)
                .load(actividadesList.get(position).getPath())
                .placeholder(R.drawable.place_holder)
                .into(holder.iviImagen);


        //TODO 2017-03-29 19:30:00
        String input_date= actividadesList.get(position).getSchedul_date();
        Date dt1 = null;
        Locale locale = new Locale ( "es" , "ES" );
        SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd", locale);

        try {
            dt1=format1.parse(input_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String dayOfTheWeek = (String) DateFormat.format("EEEE", dt1);
        String day          = (String) DateFormat.format("dd",   dt1);
        String monthString  = (String) DateFormat.format("MMM",  dt1);
        String monthNumber  = (String) DateFormat.format("MM",   dt1);
        String year         = (String) DateFormat.format("yyyy", dt1);
        String monthNumber2;
        String day2;

        LogUtils.v(TAG, " dayOfTheWeek: " + dayOfTheWeek);
        LogUtils.v(TAG, " day: " + day);
        LogUtils.v(TAG, " monthString: " + monthString);
        LogUtils.v(TAG, " monthNumber: " + monthNumber);
        LogUtils.v(TAG, " dayOfTheWeek: " + dayOfTheWeek);
        LogUtils.v(TAG, " year: " + year);
        LogUtils.v(TAG, " -------------------------------- ");
        Calendar cal = Calendar.getInstance();
        day2  = (String) DateFormat.format("dd",  cal);
        monthNumber2  = (String) DateFormat.format("MM",  cal);
        LogUtils.v(TAG, " HOY Calendar mes...: " + monthNumber2);
        LogUtils.v(TAG, " HOY Calendar dia...: " + day2);

        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        LogUtils.v(TAG, " HOY mYear...: " + mYear);
        LogUtils.v(TAG, " HOY mMonth...: " + mMonth);
        LogUtils.v(TAG, " HOY mDay...: " + mDay);
        LogUtils.v(TAG, " -------------------------------- ");
        if (day.equals(day2))
            holder.tviDate.setText("Hoy, " + actividadesList.get(position).getSchedul_time());
        else
            holder.tviDate.setText(dayOfTheWeek + " " + actividadesList.get(position).getSchedul_time());


    }

    @Override
    public int getItemCount() {
        return actividadesList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rlaContentRow) RelativeLayout rlaContentRow;
        @BindView(R.id.tviNameEvent) TextView tviNameEvent;
        @BindView(R.id.tviDate) TextView tviDate;
        @BindView(R.id.tviPrice) TextView tviPrice;
        @BindView(R.id.tviCategory) TextView tviCategory;
        @BindView(R.id.iviImagen) ImageView iviImagen;
        @BindView(R.id.iviFavorite) ImageView iviFavorite;
        @BindView(R.id.iviClock) ImageView iviClock;
        @BindView(R.id.iviTicket) ImageView iviTicket;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
