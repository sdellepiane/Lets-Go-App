package com.letsgo.appletsgo.app.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.core.BaseAppCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateSelectActivity extends BaseAppCompat {

    @BindView(R.id.dpiCalendarSince) DatePicker dpiCalendarSince;
    @BindView(R.id.tviUntilDate) TextView tviUntilDate;
    @BindView(R.id.llaPickers) LinearLayout llaPickers;

    private DatePicker dpiCalendarUntil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_select);
        ButterKnife.bind(this);

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        dpiCalendarSince.setMinDate(System.currentTimeMillis());
        dpiCalendarSince.init(mYear, mMonth, mDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if(dpiCalendarUntil != null){
                    llaPickers.removeView(dpiCalendarUntil);
                }
                dpiCalendarUntil = new DatePicker(DateSelectActivity.this);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
                params.gravity = Gravity.CENTER;
                dpiCalendarUntil.setLayoutParams(params);
                llaPickers.addView(dpiCalendarUntil);
                dpiCalendarUntil.setVisibility(View.VISIBLE);
                tviUntilDate.setVisibility(View.VISIBLE);
                Locale locale = new Locale("es", "ES");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", locale);
                Date currentDateandTime = null;
                try {
                    currentDateandTime = sdf.parse(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dpiCalendarUntil.setMinDate(currentDateandTime.getTime());
                dpiCalendarUntil.init(year, monthOfYear, dayOfMonth, new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    }
                });
            }
        });
    }
}
