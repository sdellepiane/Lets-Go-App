package com.letsgo.appletsgo.app.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.core.BaseAppCompat;
import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.model.entity.DetalleActividades;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.presenter.ActividadesPresenter;
import com.letsgo.appletsgo.view.ActividadesView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseAppCompat implements ActividadesView {
    private static String TAG = "SearchActivity : ";

    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.eteSearch)
    EditText eteSearch;

    ActividadesPresenter actividadesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initUI();
    }

    public void initUI(){
        actividadesPresenter = new ActividadesPresenter();
        actividadesPresenter.attachedView(this);
    }

    @OnClick(R.id.btnSearch)
    public void touchSearch(){
        ActividadesRaw raw = new ActividadesRaw();
        raw.setFilterPublics("1");
        raw.setFilterFree("0");
        raw.setQ(eteSearch.getText().toString().trim());

        actividadesPresenter.listCatalog(raw);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void getActividades(List<Actividades> actividadesList) {
        LogUtils.v(TAG, actividadesList.toString());
    }

    @Override
    public void detalleActividad(DetalleActividades detalleActividades) {

    }

    @Override
    public void getDistritos(List<Distrito> distritoList) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showEmptyActivitys() {

    }

    @Override
    public void showMessageError(String message) {

    }
}
