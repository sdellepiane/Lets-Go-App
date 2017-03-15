package com.letsgo.appletsgo.app.ui.activity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.component.BuildConcierto;
import com.letsgo.appletsgo.app.ui.core.BaseAppCompat;
import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.data.store.SessionUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterFirstActivity extends BaseAppCompat {
    private static final String TAG = "FilterFirstActivity";
    @BindView(R.id.rlaSubView) RelativeLayout rlaSubView;
    @BindView(R.id.rlaSubView2) RelativeLayout rlaSubView2;
    @BindView(R.id.rlaSubView3) RelativeLayout rlaSubView3;
    @BindView(R.id.btn_comenzar)
    Button btn_comenzar;

 /*   @BindView(R.id.llaSubConciertos) LinearLayout llaSubConciertos;
    @BindView(R.id.llaSubTeatro) LinearLayout llaSubTeatro;
*/
    @BindView(R.id.tviName) TextView tviName;
    @BindView(R.id.tviConciertos) TextView tviConciertos;
    @BindView(R.id.tviTeatro) TextView tviTeatro;
    @BindView(R.id.tviCineClub) TextView tviCineClub;
    @BindView(R.id.tviDanza) TextView tviDanza;
    @BindView(R.id.tviExposiciones) TextView tviExposiciones;
    @BindView(R.id.tviFiestas) TextView tviFiestas;
    @BindView(R.id.tviPaseos) TextView tviPaseos;
    @BindView(R.id.tviDeportes) TextView tviDeportes;
    @BindView(R.id.tviLiteratura) TextView tviLiteratura;
    @BindView(R.id.tviCirco) TextView tviCirco;
    @BindView(R.id.tviTalleres) TextView tviTalleres;
    @BindView(R.id.tviConferencias) TextView tviConferencias;
    @BindView(R.id.tviStandUp) TextView tviStandUp;
    @BindView(R.id.tviCuentacuentos) TextView tviCuentacuentos;

    private boolean statusConcierto = false ;
    private boolean statusTeatro = false ;
    private boolean statusCineBclub = false ;
    private boolean statusDanza = false ;
    private boolean statusExposicion = false ;
    private boolean statusFiesta = false ;
    private boolean statusPaseos = false ;
    private boolean statusDeportes = false ;
    private boolean statusLiteratura = false ;
    private boolean statusTalleres = false ;
    private boolean statusCirco = false ;
    private boolean statusConferencias= false ;
    private boolean statusStandUp = false ;
    private boolean statusCuentacuentos= false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_first);
        ButterKnife.bind(this);

        initOnclickListenerViews(btn_comenzar, tviConciertos, tviTeatro, tviCineClub, tviDanza, tviExposiciones, tviFiestas, tviDeportes, tviLiteratura, tviTalleres,
                tviPaseos, tviCirco, tviConferencias, tviStandUp, tviCuentacuentos);



        tviName.setText("Hola   " + SessionUser.getSessionUser(this).getFirst_name());

        //TODO 2017-03-29 19:30:00
        String input_date="01-08-2017";
        Date dt1 = null;
        Locale locale = new Locale ( "es" , "ES" );
        SimpleDateFormat format1=new SimpleDateFormat("dd-MM-yyyy", locale);

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
        LogUtils.v(TAG, " fecha: " + dayOfTheWeek);
        LogUtils.v(TAG, " fecha: " + day);
        LogUtils.v(TAG, " fecha: " + monthString);
        LogUtils.v(TAG, " fecha: " + monthNumber);
        LogUtils.v(TAG, " fecha: " + dayOfTheWeek);
        LogUtils.v(TAG, " fecha: " + year);

        /*try {
            String _24HourTime = "20:00";
            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
            SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
            Date _24HourDt = _24HourSDF.parse(_24HourTime);
            System.out.println(_24HourDt);
            System.out.println(_12HourSDF.format(_24HourDt));
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtils.v(TAG, " fecha: " + _24HourDt);
        LogUtils.v(TAG, " fecha: " + _12HourSDF.format(_24HourDt));*/


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tviConciertos:
                statusConcierto = showSubCategory(statusConcierto, tviConciertos, R.layout.layout_sub_concierto);
                break;
            case R.id.tviTeatro:
                statusTeatro = showSubCategory(statusTeatro, tviTeatro, R.layout.layout_sub_teatro);
                break;
            case R.id.tviCineClub:
                statusCineBclub = showSubCategory(statusCineBclub, tviCineClub, R.layout.layout_sub_cineclub);
                break;
            case R.id.tviDanza:
                statusDanza = showSubCategory(statusDanza, tviDanza, R.layout.layout_sub_danza);
                break;
            case R.id.tviExposiciones:
                statusExposicion = showSubCategory2(statusExposicion, tviExposiciones, R.layout.layout_sub_expo);
                break;
            case R.id.tviFiestas:
                statusFiesta = showSubCategory2(statusFiesta, tviFiestas, R.layout.layout_sub_fiesta);
                break;
            case R.id.tviDeportes:
                statusDeportes = showSubCategory3(statusDeportes, tviDeportes, R.layout.layout_sub_deportes);
                break;
            case R.id.tviLiteratura:
                statusLiteratura = showSubCategory3(statusLiteratura, tviLiteratura, R.layout.layout_sub_literatura);
                break;
            case R.id.tviTalleres:
                statusTalleres = showSubCategory3(statusTalleres, tviTalleres, R.layout.layout_sub_talleres);
                break;

            case R.id.tviPaseos:
                statusPaseos = showSubCategoryChange(statusPaseos, tviPaseos);
                break;
            case R.id.tviCirco:
                statusCirco = showSubCategoryChange(statusCirco, tviCirco);
                break;
            case R.id.tviConferencias:
                statusConferencias = showSubCategoryChange(statusConferencias, tviConferencias);
                break;
            case R.id.tviStandUp:
                statusStandUp = showSubCategoryChange(statusStandUp, tviStandUp);
                break;
            case R.id.tviCuentacuentos:
                statusCuentacuentos = showSubCategoryChange(statusCuentacuentos, tviCuentacuentos);
                break;

            case R.id.btn_comenzar:
                nextActivity(HomeActivity.class, false);
                break;
        }
    }

    public boolean showSubCategoryChange(boolean status, TextView tviCategory){
        rlaSubView.removeAllViews();
        rlaSubView2.removeAllViews();
        rlaSubView3.removeAllViews();
        boolean newStatus;
        if (status == false) {
            newStatus = true;
            tviCategory.setTextColor(getResources().getColor(R.color.white));
            tviCategory.setBackgroundResource(R.drawable.type_public_on);
        }else{
            rlaSubView.removeAllViews();
            newStatus = false;

            tviCategory.setTextColor(getResources().getColor(R.color.secondary_text));
            tviCategory.setBackgroundResource(R.drawable.type_public_off);
        }
        return newStatus;
    }

    public boolean showSubCategory(boolean status, TextView tviCategory, int layout){
        BuildConcierto buildConcierto = new BuildConcierto();
        rlaSubView.removeAllViews();
        rlaSubView2.removeAllViews();
        rlaSubView3.removeAllViews();
        boolean newStatus;
        if (status == false) {
            newStatus = true;
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    (FrameLayout.LayoutParams.MATCH_PARENT), (FrameLayout.LayoutParams.WRAP_CONTENT));
            View customView = null;
            switch (layout) {
                case R.layout.layout_sub_concierto:
                    customView = buildConcierto.buildConcierto(layout,this);
                    break;
                case R.layout.layout_sub_teatro:
                    customView = buildConcierto.buildConcierto(layout,this);
                    break;
                case R.layout.layout_sub_cineclub:
                    customView = buildConcierto.buildConcierto(layout,this);
                    break;
                case R.layout.layout_sub_danza:
                    customView = buildConcierto.buildConcierto(layout,this);
                    break;
            }
            customView.setLayoutParams(lp);

            rlaSubView.addView(customView);

            tviCategory.setTextColor(getResources().getColor(R.color.white));
            tviCategory.setBackgroundResource(R.drawable.type_public_on);

        }else{
            rlaSubView.removeAllViews();
            newStatus = false;

            tviCategory.setTextColor(getResources().getColor(R.color.secondary_text));
            tviCategory.setBackgroundResource(R.drawable.type_public_off);
        }
        return newStatus;
    }

    public boolean showSubCategory2(boolean status, TextView tviCategory, int layout){
        BuildConcierto buildConcierto = new BuildConcierto();
        rlaSubView.removeAllViews();
        rlaSubView2.removeAllViews();
        rlaSubView3.removeAllViews();
        boolean newStatus;
        if (status == false) {
            newStatus = true;
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    (FrameLayout.LayoutParams.MATCH_PARENT), (FrameLayout.LayoutParams.WRAP_CONTENT));
            View customView = null;

            switch (layout) {
                case R.layout.layout_sub_expo:
                    customView = buildConcierto.buildConcierto(layout,this);
                    break;
                case R.layout.layout_sub_fiesta:
                    customView = buildConcierto.buildConcierto(layout,this);
                    break;
                case R.layout.layout_sub_deportes:
                    customView = buildConcierto.buildConcierto(layout,this);
                    break;
                case R.layout.layout_sub_literatura:
                    customView = buildConcierto.buildConcierto(layout,this);
                    break;
                case R.layout.layout_sub_talleres:
                    customView = buildConcierto.buildConcierto(layout,this);
                    break;
            }
            customView.setLayoutParams(lp);

            rlaSubView2.addView(customView);

            tviCategory.setTextColor(getResources().getColor(R.color.white));
            tviCategory.setBackgroundResource(R.drawable.type_public_on);

        }else{
            rlaSubView2.removeAllViews();
            newStatus = false;

            tviCategory.setTextColor(getResources().getColor(R.color.secondary_text));
            tviCategory.setBackgroundResource(R.drawable.type_public_off);
        }
        return newStatus;
    }

    public boolean showSubCategory3(boolean status, TextView tviCategory, int layout){
        BuildConcierto buildConcierto = new BuildConcierto();
        rlaSubView.removeAllViews();
        rlaSubView2.removeAllViews();
        rlaSubView3.removeAllViews();
        boolean newStatus;
        if (status == false) {
            newStatus = true;
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    (FrameLayout.LayoutParams.MATCH_PARENT), (FrameLayout.LayoutParams.WRAP_CONTENT));
            View customView = null;
            switch (layout) {
                case R.layout.layout_sub_deportes:
                    customView = buildConcierto.buildConcierto(layout,this);
                    break;
                case R.layout.layout_sub_literatura:
                    customView = buildConcierto.buildConcierto(layout,this);
                    break;
                case R.layout.layout_sub_talleres:
                    customView = buildConcierto.buildConcierto(layout,this);
                    break;
            }
            customView.setLayoutParams(lp);
            rlaSubView3.addView(customView);

            tviCategory.setTextColor(getResources().getColor(R.color.white));
            tviCategory.setBackgroundResource(R.drawable.type_public_on);

        }else{
            rlaSubView3.removeAllViews();
            newStatus = false;

            tviCategory.setTextColor(getResources().getColor(R.color.secondary_text));
            tviCategory.setBackgroundResource(R.drawable.type_public_off);
        }
        return newStatus;
    }

/*    private View BuildConcierto(int layout){
        View item = LayoutInflater.from(this).inflate(layout,null);
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
        @Nullable @BindView(R.id.tviElectro) TextView tviElectro;
        @Nullable @BindView(R.id.tviComedia) TextView tviComedia;
        public ViewHolder(View container) {
            ButterKnife.bind(this,container);
        }
    }*/


    private View buildCTeatro(int layout){
        View item = LayoutInflater.from(this).inflate(layout,null);
        final ViewHolderTeatro holder = new ViewHolderTeatro(item);
        holder.tviComedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.v("CLICK", holder.tviComedia.getText().toString());
            }
        });
        return item;
    }

    public class ViewHolderTeatro {
        @BindView(R.id.tviComedia) TextView tviComedia;

        public ViewHolderTeatro(View container) {
            ButterKnife.bind(this,container);
        }
    }

}
