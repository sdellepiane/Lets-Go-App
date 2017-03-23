package com.letsgo.appletsgo.app.ui.activity;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.component.HorarioComponent;
import com.letsgo.appletsgo.app.ui.component.PreciosComponent;
import com.letsgo.appletsgo.app.ui.core.BaseAppCompat;
import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.DetalleActividadRaw;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.model.entity.DetalleActividades;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.presenter.ActividadesPresenter;
import com.letsgo.appletsgo.view.ActividadesView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailEvent2Activity extends BaseAppCompat implements ActividadesView {
    private static final String TAG = "DetailEvent2Activity";
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.appbarLayout) AppBarLayout appbarLayout;
    @BindView(R.id.tviTileToolbar) TextView tviTileToolbar;
    @BindView(R.id.tviTileBanner) TextView tviTileBanner;
    @BindView(R.id.iviImagen) ImageView iviImagen;
    @BindView(R.id.iviFavorite) ImageView iviFavorite;
    @BindView(R.id.iviShared) ImageView iviShared;
    @BindView(R.id.tviCategory) TextView tviCategory;
    @BindView(R.id.tviDescripcion) TextView tviDescripcion;
    @BindView(R.id.tviNamePlace) TextView tviNamePlace;
    @BindView(R.id.tviAddressPlace) TextView tviAddressPlace;
    @BindView(R.id.tviVerMas) TextView tviVerMas;
    @BindView(R.id.tviPriceTitle) TextView tviPriceTitle;
    @BindView(R.id.viewContenedor) View viewContenedor;
    @BindView(R.id.btnEntradas) Button btnEntradas;
    @BindView(R.id.rlaLoading) RelativeLayout viewLoading;
    @BindView(R.id.llaHorarioCompoent) HorarioComponent llaHorarioCompoent;
    @BindView(R.id.llaPrecioComponent) PreciosComponent llaPrecioComponent;

    private ImageButton locButton;
    private ActividadesPresenter actividadesPresenter;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event2);
        ButterKnife.bind(this);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.ColapseAppBar);

        appbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == -collapsingToolbarLayout.getHeight() + toolbar.getHeight()) {
                    iviFavorite.setVisibility(View.VISIBLE);
                    iviShared.setVisibility(View.VISIBLE);
                    tviTileToolbar.setVisibility(View.VISIBLE);
                }else if (verticalOffset == 0) {
                    iviFavorite.setVisibility(View.GONE);
                    iviShared.setVisibility(View.GONE);
                    tviTileToolbar.setVisibility(View.GONE);
                }
            }
        });

        initOnclickListenerViews(tviVerMas, iviFavorite);
        initPresenter();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);









        loadBackdrop();
    }

    public void initPresenter(){
        Bundle extras = getIntent().getExtras();
        String id_actividad = extras.getString("ID_ACTIVIDAD");
        actividadesPresenter = new ActividadesPresenter();
        actividadesPresenter.attachedView(this);

        DetalleActividadRaw raw = new DetalleActividadRaw();
        raw.setId_activities(id_actividad);
        //raw.setId_activities("107");
        actividadesPresenter.detalleActividad(raw);

    }

    private void loadBackdrop() {
        /*Picasso.with(this)
                .load(R.drawable.banner_test7)
                .into(backdrop);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tviVerMas:
                if (tviDescripcion.getMaxLines() == 5) {
                    tviDescripcion.setMaxLines(100);
                    tviVerMas.setText("Leer menos");
                }else{
                    tviDescripcion.setMaxLines(5);
                    tviVerMas.setText("Leer mas");
                }
                break;
            case R.id.iviFavorite:
                LogUtils.v(TAG, "CLICK FAVORITE");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        locButton = (ImageButton) menu.findItem(R.id.menuFavorite).getActionView();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuFavorite:
                // do whatever
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void getActividades(List<Actividades> tiendaList) {

    }

    @Override
    public void detalleActividad(DetalleActividades detalleActividades) {
        LogUtils.v(TAG, detalleActividades.toString());
        Picasso.with(this)
                .load(detalleActividades.getImg().get(0).getPath())
                .placeholder(R.drawable.place_holder)
                .into(iviImagen);

        collapsingToolbarLayout.setTitle(detalleActividades.getActivity());
        tviTileBanner.setText(detalleActividades.getActivity());
        tviTileToolbar.setText(detalleActividades.getActivity());
        tviCategory.setText(detalleActividades.getTypes() + " | " + (detalleActividades.getSubtypes()));
        tviDescripcion.setText(detalleActividades.getLong_description());
        tviNamePlace.setText(detalleActividades.getPlaces().get(0).getName_place());
        tviAddressPlace.setText(detalleActividades.getPlaces().get(0).getAddresses());
        llaHorarioCompoent.init(detalleActividades.getPlaces().get(0).getGroup_date().get(0).getSchedule_date(),detalleActividades.getPlaces().get(0).getGroup_date().get(0).getSchedule_time());

        if (tviDescripcion.length() < 100 ) {
            tviVerMas.setVisibility(View.GONE);
        }

        if (detalleActividades.getPlaces().get(0).getPrices().get(0).getPrice().equals("0.00")){
            llaPrecioComponent.setVisibility(View.GONE);
            tviPriceTitle.setVisibility(View.GONE);
            btnEntradas.setText("INGRESO LIBRE");
        }else{
            btnEntradas.setText("ENTRADAS");
            llaPrecioComponent.init(detalleActividades.getPlaces().get(0).getPrices());
        }

    }

    @Override
    public void getDistritos(List<Distrito> distritoList) {

    }

    @Override
    public void showLoading() {
        viewLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        viewLoading.setVisibility(View.GONE);
        viewContenedor.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessageError(String message) {

    }

    @Override
    public Context getContext() {
        return this;
    }


}