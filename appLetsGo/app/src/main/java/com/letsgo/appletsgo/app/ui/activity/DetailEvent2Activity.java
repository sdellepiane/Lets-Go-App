package com.letsgo.appletsgo.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.component.HorarioComponent;
import com.letsgo.appletsgo.app.ui.component.PreciosComponent;
import com.letsgo.appletsgo.app.ui.core.BaseAppCompat;
import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.DetalleActividadRaw;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.model.entity.CategoriesToPreferences;
import com.letsgo.appletsgo.domain.model.entity.DetalleActividades;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.presenter.ActividadesPresenter;
import com.letsgo.appletsgo.view.ActividadesView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailEvent2Activity extends BaseAppCompat implements ActividadesView {
    private static final String TAG = "DetailEvent2Activity";
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.appbarLayout) AppBarLayout appbarLayout;
    @BindView(R.id.tviTileToolbar) TextView tviTileToolbar;
    @BindView(R.id.tviTileBanner) TextView tviTileBanner;
    @BindView(R.id.iviAdvertisements_1) ImageView iviAdvertisements_1;
    @BindView(R.id.iviAdvertisements_2) ImageView iviAdvertisements_2;
    @BindView(R.id.iviMenu) ImageView iviMenu;
    @BindView(R.id.iviImagen) ImageView iviImagen;
    @BindView(R.id.iviFavorite) ImageView iviFavorite;
    @BindView(R.id.iviShared) ImageView iviShared;
    @BindView(R.id.iviSharedInImagen) ImageView iviSharedInImagen;
    @BindView(R.id.tviCategory) TextView tviCategory;
    @BindView(R.id.tviDescripcion) TextView tviDescripcion;
    @BindView(R.id.tviNamePlace) TextView tviNamePlace;
    @BindView(R.id.tviAddressPlace) TextView tviAddressPlace;
    @BindView(R.id.tviVerMas) TextView tviVerMas;
    @BindView(R.id.tviPriceTitle) TextView tviPriceTitle;
    @BindView(R.id.viewContenedor) View viewContenedor;
    @BindView(R.id.btnEntradas) Button btnEntradas;
    @BindView(R.id.rlaLoading) RelativeLayout viewLoading;
    @BindView(R.id.llaMap) LinearLayout llaMap;
    @BindView(R.id.llaHorarioCompoent) HorarioComponent llaHorarioCompoent;
    @BindView(R.id.llaPrecioComponent) PreciosComponent llaPrecioComponent;

    private ImageButton locButton;
    private ActividadesPresenter actividadesPresenter;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    String longitude;
    String latitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event2);
        ButterKnife.bind(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        // this part is optional
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }});

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


    public void sharedEventFacebook(){
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle("Hello Facebook")
                    .setContentDescription(
                            "Lets Go, sample  showcases simple Facebook integration")
                    .setContentUrl(Uri.parse("https://www.facebook.com/CINESCAPE.PERU/?fref=ts"))
                    .build();

            shareDialog.show(linkContent);
        }
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

    @OnClick(R.id.iviMenu)
    public void onClickIviMenu(){
        onBackPressed();
    }

    @OnClick(R.id.llaMap)
    public void initMap(){
        String uri = "http://maps.google.com/maps?q=loc:"+latitude+","+longitude;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        intent.setData(Uri.parse(uri));
        startActivity(intent);

//        try
//        {
//            String url = "waze://?q=Hawaii";
//            Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
//            startActivity( intent );
//        }
//        catch ( ActivityNotFoundException ex  )
//        {
//            Intent intent =
//                    new Intent( Intent.ACTION_VIEW, Uri.parse( "market://details?id=com.waze" ) );
//            startActivity(intent);
//        }
    }

    @OnClick(R.id.iviSharedInImagen)
    public void setbannerShared(){
        sharedEventFacebook();
    }

    @OnClick(R.id.iviShared)
    public void iviShared(){
        sharedEventFacebook();
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
        latitude = detalleActividades.getPlaces().get(0).getLatitude();
        longitude = detalleActividades.getPlaces().get(0).getLongitude();
        LogUtils.v(TAG, detalleActividades.toString());
        Picasso.with(this)
                .load(detalleActividades.getImg().get(0).getPath())
                .placeholder(R.drawable.place_holder)
                .into(iviImagen);

        Picasso.with(this)
                .load(detalleActividades.getAdvertisements().get(0).getPath())
                .placeholder(R.drawable.place_holder)
                .into(iviAdvertisements_1);

        Picasso.with(this)
                .load(detalleActividades.getAdvertisements().get(1).getPath())
                .placeholder(R.drawable.place_holder)
                .into(iviAdvertisements_2);


        collapsingToolbarLayout.setTitle(detalleActividades.getActivity());
        tviTileBanner.setText(detalleActividades.getActivity());
        tviTileToolbar.setText(detalleActividades.getActivity());
        tviCategory.setText(detalleActividades.getTypes() + " | " + (detalleActividades.getSubtypes()));
        tviDescripcion.setText(detalleActividades.getLong_description());
        tviNamePlace.setText(detalleActividades.getPlaces().get(0).getName_place());
        tviAddressPlace.setText(detalleActividades.getPlaces().get(0).getAddresses());


        LogUtils.v(TAG, "Horarios: " + detalleActividades.getPlaces().get(0).toString());
        LogUtils.v(TAG, "Horarios: " + detalleActividades.getPlaces().get(0).getGroup_date().get(0).getSchedule_time());

        llaHorarioCompoent.init(detalleActividades.getPlaces().get(0).getGroup_date().get(0).getSchedule_date(),detalleActividades.getPlaces().get(0).getGroup_date());

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

    }

    @Override
    public void deleteFavorite(Actividades actividades, int actividadId) {

    }

    @Override
    public void assignFavorites(List<Actividades> actividadesList) {

    }

    @Override
    public void saveFavoriteSuccess() {

    }

    @Override
    public void deleteFavoriteSuccess() {

    }

    @Override
    public Context getContext() {
        return this;
    }


}
