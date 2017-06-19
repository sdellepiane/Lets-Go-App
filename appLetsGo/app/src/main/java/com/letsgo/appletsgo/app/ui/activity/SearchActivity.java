package com.letsgo.appletsgo.app.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.adapter.EventAdapter;
import com.letsgo.appletsgo.app.ui.core.BaseAppCompat;
import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.app.utils.RecyclerClickListener;
import com.letsgo.appletsgo.app.utils.RecyclerTouchListener;
import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.model.entity.CategoriesToPreferences;
import com.letsgo.appletsgo.domain.model.entity.DetalleActividades;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.presenter.ActividadesPresenter;
import com.letsgo.appletsgo.view.ActividadesView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseAppCompat implements ActividadesView{
    private static String TAG = "SearchActivity";

    @BindView(R.id.rviEvents)
    RecyclerView rviEvents;
    @BindView(R.id.eteSearch)
    EditText eteSearch;
    @BindView(R.id.rlaBack)
    RelativeLayout rlaBack;
    @BindView(R.id.rlaSearch)
    RelativeLayout rlaSearch;
    @BindView(R.id.rlaBackgroundSearch)
    RelativeLayout rlaBackgroundSearch;

    private List<Actividades> actividadesList = new ArrayList<>();
    private ActividadesPresenter actividadesPresenter;
    private Actividades favorite;
    private EventAdapter eventAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initViewEvents();
        initPresenter();
        eteSearch.findFocus();
        eteSearch.setFocusable(true);
        showSoftKeyboard(eteSearch);
        eteSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideSoftKeyboard(eteSearch);
                    ActividadesRaw raw = new ActividadesRaw();
                    raw.setQ(eteSearch.getText().toString());
                    actividadesPresenter.listCatalog(raw);
                    return true;
                }
                return false;
            }
        });
    }

    public void initPresenter() {
        actividadesPresenter = new ActividadesPresenter();
        actividadesPresenter.attachedView(this);

    }

    public void initViewEvents() {
        rviEvents.setHasFixedSize(true);
        rviEvents.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rviEvents.addOnItemTouchListener(new RecyclerTouchListener(this, rviEvents, new RecyclerClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }

    @OnClick(R.id.rlaBack)
    public void onClickBack(){
        onBackPressed();
    }

    @OnClick(R.id.rlaSearch)
    public void onClickSearchEvents(){
        hideSoftKeyboard(eteSearch);
        ActividadesRaw raw = new ActividadesRaw();
        raw.setQ(eteSearch.getText().toString());
        raw.setQuantity("80");
        actividadesPresenter.listCatalog(raw);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void getActividades(List<Actividades> tiendaList) {
        LogUtils.v(TAG, " Lista actividades" + tiendaList.toString());
        rlaBackgroundSearch.setVisibility(View.GONE);
        actividadesList = tiendaList;
        actividadesPresenter.assignFavorites(this.actividadesList);
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
    public void getCategoriesFromPreferences(CategoriesToPreferences categoriesToPreferences) {

    }

    @Override
    public void showMessageError(String message) {

    }

    @Override
    public void saveFavorite(Actividades actividades) {
        this.favorite = actividades;
        actividadesPresenter.saveFavorite(actividades);
    }

    @Override
    public void deleteFavorite(Actividades actividades, int actividadId) {

    }

    @Override
    public void assignFavorites(List<Actividades> actividadesList) {
        this.actividadesList = actividadesList;
        eventAdapter = new EventAdapter(this, this.actividadesList, this);
        rviEvents.setAdapter(eventAdapter);
    }

    @Override
    public void saveFavoriteSuccess() {

    }

    @Override
    public void deleteFavoriteSuccess() {

    }
}
