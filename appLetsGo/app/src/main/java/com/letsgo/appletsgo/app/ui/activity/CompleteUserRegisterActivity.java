package com.letsgo.appletsgo.app.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.core.BaseAppCompat;
import com.letsgo.appletsgo.app.utils.LogUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompleteUserRegisterActivity extends BaseAppCompat {
    @BindView(R.id.datePicker) DatePicker datePicker;
    @BindView(R.id.tviHappy) TextView tviHappy;
    @BindView(R.id.vHappy) View vHappy;
    @BindView(R.id.btnGetHappy)
    Button btnGetHappy;

    String fechaNacimiento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_user_register);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.tviHappy)
    public void onClickTviHappy(){
        vHappy.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnGetHappy)
    public void onClickBtnGetHappy(){
        SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy");
        fechaNacimiento = df.format(new Date(datePicker.getYear() - 1900, datePicker.getMonth(), datePicker.getDayOfMonth()));
        LogUtils.v("Fecha", fechaNacimiento);
    }

    @OnClick(R.id.btnGetHappy)
    public void getFechaNacimiento(){
        vHappy.setVisibility(View.GONE);
        tviHappy.setText(fechaNacimiento);
    }
}
