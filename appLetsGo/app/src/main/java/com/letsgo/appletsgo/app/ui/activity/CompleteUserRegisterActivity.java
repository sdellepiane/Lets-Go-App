package com.letsgo.appletsgo.app.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.adapter.DistritoAdapter;
import com.letsgo.appletsgo.app.ui.core.BaseAppCompat;
import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.app.utils.RecyclerClickListener;
import com.letsgo.appletsgo.app.utils.RecyclerTouchListener;
import com.letsgo.appletsgo.data.entity.raw.CompleteUserRaw;
import com.letsgo.appletsgo.data.store.SessionUser;
import com.letsgo.appletsgo.domain.model.entity.CompleteUser;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.domain.model.entity.Login;
import com.letsgo.appletsgo.domain.model.entity.User;
import com.letsgo.appletsgo.presenter.LoginPresenter;
import com.letsgo.appletsgo.view.LoginView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompleteUserRegisterActivity extends BaseAppCompat implements LoginView {
    @BindView(R.id.datePicker) DatePicker datePicker;
    @BindView(R.id.rlaDistritoLima) RelativeLayout rlaDistritoLima;
    @BindView(R.id.tviHappy) TextView tviHappy;
    @BindView(R.id.tviDistristo) TextView tviDistristo;
    @BindView(R.id.vHappy) View vHappy;
    @BindView(R.id.vLoading) View vLoading;
    @BindView(R.id.rviDistritos) RecyclerView rviDistritos;
    @BindView(R.id.btnNext) Button btnNext;
    @BindView(R.id.btnGetHappy) Button btnGetHappy;


    String fechaNacimiento;
    String idUbigeo = "";

    List<Distrito> distritoList = new ArrayList<>();

    LoginPresenter loginPresenter = new LoginPresenter();
    DistritoAdapter distritoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_user_register);
        ButterKnife.bind(this);
        loginPresenter.attachedView(this);
        loginPresenter.getDistrito();

        rviDistritos.addOnItemTouchListener(new RecyclerTouchListener(this, rviDistritos, new RecyclerClickListener() {
            @Override
            public void onClick(View view, int position) {
                rlaDistritoLima.setVisibility(View.GONE);
                btnNext.setVisibility(View.VISIBLE);
                tviDistristo.setText(distritoList.get(position).getDescription());
                idUbigeo = distritoList.get(position).getId_ubigeos();
                LogUtils.v("ID_UBIGEO: ", idUbigeo);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }


    @OnClick(R.id.tviHappy)
    public void onClickTviHappy(){
        btnNext.setVisibility(View.GONE);
        vHappy.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnGetHappy)
    public void onClickBtnGetHappy(){
        //impleDateFormat df = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyy MM dd");
        fechaNacimiento = df.format(new Date(datePicker.getYear() - 1900, datePicker.getMonth(), datePicker.getDayOfMonth()));
        LogUtils.v("Fecha", fechaNacimiento);
        LogUtils.v("Fecha", fechaNacimiento.replace(" ", "-"));
        vHappy.setVisibility(View.GONE);
        btnNext.setVisibility(View.VISIBLE);
        tviHappy.setText(fechaNacimiento.replace(" ", "-"));
    }



    @OnClick(R.id.tviDistristo)
    public void onClickDistrito(){
        btnNext.setVisibility(View.GONE);
        rlaDistritoLima.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnNext)
    public void onClickNext(){
        if (!idUbigeo.isEmpty() && !fechaNacimiento.isEmpty()) {
            CompleteUserRaw raw = new CompleteUserRaw();
            raw.setId_users(SessionUser.getSessionUser(this).getIdUser());
            raw.setId_ubigeos(idUbigeo);
            raw.setBirthdate(fechaNacimiento.replace(" ", "-"));
            loginPresenter.setCompleteUserRegister(raw);
        }else{
            snackBarError("Por favor, complete campos", rviDistritos);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void loginFacebook(Login login) {

    }

    @Override
    public void completeUserRegister(CompleteUser completeUser) {
        LogUtils.v("Fecha", fechaNacimiento);
        User user = SessionUser.getSessionUser(this);
        user.setBirthdate(fechaNacimiento.replace(" ", "-"));
        user.setId_ubigeosh(idUbigeo);
        SessionUser.saveSessionUser(this, user);
        nextActivity(FilterFirstActivity.class, false);
    }

    @Override
    public void distritosLima(List<Distrito> distritoList) {
        LogUtils.v("distritosLima", distritoList.toString());
        this.distritoList = distritoList;
        distritoAdapter = new DistritoAdapter(this, distritoList);
        rviDistritos.setHasFixedSize(true);
        rviDistritos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rviDistritos.setAdapter(distritoAdapter);
    }



    @Override
    public void showLoading() {
        vLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        vLoading.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
