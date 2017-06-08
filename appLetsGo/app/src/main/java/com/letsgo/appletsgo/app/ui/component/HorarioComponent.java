package com.letsgo.appletsgo.app.ui.component;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.utils.ScreenUtils;
import com.letsgo.appletsgo.domain.model.entity.DateGroup;
import com.letsgo.appletsgo.domain.model.entity.TimesGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by louislopez on 6/03/17.
 */

public class HorarioComponent extends LinearLayout {
    private String fecha;
    private List<DateGroup> dateGroupList;
    private List<TimesGroup> timesGroupList;
    private List<View> childrens;
    private Context context;

    public HorarioComponent(Context context) {
        super(context);
    }

    public HorarioComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorarioComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

/*    public void init(String fecha, List<TimesGroup> timesGroupList){
        this.timesGroupList = timesGroupList;
        this.fecha = fecha;
        this.context = context;
        populate();
    }*/

   public void init(String fecha, List<DateGroup> dateGroupList){
        this.dateGroupList = dateGroupList;
        //this.fecha = fecha;
        this.context = context;
        populate();
    }

/*    private void populate(){
        int bottom= ScreenUtils.dpToPx(10);
        childrens= new ArrayList<>();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((FrameLayout.LayoutParams.WRAP_CONTENT), (FrameLayout.LayoutParams.WRAP_CONTENT));
        lp.setMargins(0, 0, bottom, 0);
        View child;
        TimesGroup timesGroup;
        for (int i = 0 ; i < this.timesGroupList.size() ; i++){
            timesGroup = this.timesGroupList.get(i);
            child= buildCombosChild(timesGroup);
            child.setLayoutParams(lp);
            this.addView(child);
            childrens.add(child);
        }
        this.requestLayout();
    }*/

    public void populate(){
        int minimaMostrar = this.dateGroupList.size();
        int bottom= ScreenUtils.dpToPx(10);
        childrens= new ArrayList<>();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((FrameLayout.LayoutParams.WRAP_CONTENT), (FrameLayout.LayoutParams.WRAP_CONTENT));
        lp.setMargins(0, 0, bottom, 0);
        View child;
        DateGroup dateGroup;

        if (this.dateGroupList.size() > 10)
            minimaMostrar = 10;

        for (int i = 0 ; i < minimaMostrar ; i++){
            dateGroup = this.dateGroupList.get(i);
            for (int j = 0 ; j < dateGroup.getSchedule_time().size() ; j++){
                child= buildCombosChild(dateGroup.getSchedule_date(), dateGroup.getSchedule_time().get(j));
                child.setLayoutParams(lp);
                this.addView(child);
                childrens.add(child);
            }
        }
    }

    private View buildCombosChild(String fecha,final  TimesGroup timesGroup){
         int mDay;
         int month;
         int mYear;
         int year1;
         int mMonth;

        String input_date= fecha;
        Date dt1 = null;
        Locale locale = new Locale ( "es" , "ES" );
        SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd", locale);

        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        try {
            dt1=format1.parse(input_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String dayOfTheWeek = (String) DateFormat.format("EEE", dt1);
        String day          = (String) DateFormat.format("dd",   dt1);
        String monthString  = (String) DateFormat.format("MMM",  dt1);
        String monthNumber  = (String) DateFormat.format("MM",   dt1);
        String year         = (String) DateFormat.format("yyyy", dt1);
        String monthNumber2;
        String day2;


        View item = LayoutInflater.from(getContext()).inflate(R.layout.layout_horario_activiy,null);
        item.setTag(timesGroup);

        final ViewHolder holder = new ViewHolder(item);
        holder.tviDia.setText(dayOfTheWeek);
        holder.tviDiaNum.setText(day);
        holder.tviMes.setText(monthString);
        holder.tviHora.setText(timesGroup.getSchedul_time());

        return item;
    }

    private class ViewHolder {
        public TextView tviDia;
        public TextView tviDiaNum;
        public TextView tviMes;
        public TextView tviHora;


        public ViewHolder(View container) {
            this.tviDia = (TextView)container.findViewById(R.id.tviDia);
            this.tviDiaNum = (TextView)container.findViewById(R.id.tviDiaNum);
            this.tviMes = (TextView)container.findViewById(R.id.tviMes);
            this.tviHora = (TextView)container.findViewById(R.id.tviHora);
        }
    }
}
