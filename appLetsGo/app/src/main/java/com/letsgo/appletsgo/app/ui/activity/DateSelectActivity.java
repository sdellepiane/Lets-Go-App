package com.letsgo.appletsgo.app.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.core.BaseAppCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DateSelectActivity extends BaseAppCompat {

    @BindView(R.id.dpiCalendarSince) DatePicker dpiCalendarSince;
    @BindView(R.id.tviUntilDate) TextView tviUntilDate;
    @BindView(R.id.llaPickers) LinearLayout llaPickers;
    @BindView(R.id.butSelectDates) Button butSelectDates;

    private DatePicker dpiCalendarUntil;
    private String sinceDateSelected = "";
    private String untilDateSelected = "";

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
                String month = (monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : "" + (monthOfYear + 1);
                String day = dayOfMonth < 10 ? "0" + dayOfMonth : "" + dayOfMonth;
                sinceDateSelected = year + "-" + month + "-" + day;
                butSelectDates.setVisibility(View.VISIBLE);
                if(dpiCalendarUntil != null){
                    llaPickers.removeView(dpiCalendarUntil);
                }
                dpiCalendarUntil = new DatePicker(DateSelectActivity.this);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
                params.gravity = Gravity.CENTER;
                params.setMargins(0, 0, 0, 60);
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
                        String month = (monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : "" + (monthOfYear + 1);
                        String day = dayOfMonth < 10 ? "0" + dayOfMonth : "" + dayOfMonth;
                        untilDateSelected = year + "-" + month + "-" + day;
                    }
                });
            }
        });
    }

    @OnClick(R.id.butSelectDates)
    public void confirmDates(){
        if(untilDateSelected.equals("")){
            Toast.makeText(DateSelectActivity.this, getString(R.string.sNotSelectUntilDate), Toast.LENGTH_SHORT).show();
        } else{
            Intent intent=new Intent();
            intent.putExtra("sinceDate", sinceDateSelected);
            intent.putExtra("untilDate", untilDateSelected);
            setResult(2, intent);
            finish();
        }
    }
}
