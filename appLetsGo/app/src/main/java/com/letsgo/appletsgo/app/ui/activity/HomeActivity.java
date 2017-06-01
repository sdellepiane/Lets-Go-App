package com.letsgo.appletsgo.app.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Point;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.adapter.EventAdapter;
import com.letsgo.appletsgo.app.ui.component.DistritoComponent;
import com.letsgo.appletsgo.app.ui.core.BaseAppCompat;
import com.letsgo.appletsgo.app.utils.CircleTransform;
import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.app.utils.RecyclerClickListener;
import com.letsgo.appletsgo.app.utils.RecyclerTouchListener;
import com.letsgo.appletsgo.app.utils.ScreenUtils;
import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.CategoriesRaw;
import com.letsgo.appletsgo.data.entity.raw.PlacesRaw;
import com.letsgo.appletsgo.data.entity.raw.SubcategoriesRaw;
import com.letsgo.appletsgo.data.entity.raw.filterPlacesRaw;
import com.letsgo.appletsgo.data.store.SessionUser;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.model.entity.Categories;
import com.letsgo.appletsgo.domain.model.entity.CategoriesToPreferences;
import com.letsgo.appletsgo.domain.model.entity.DetalleActividades;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.domain.model.entity.DistritosSession;
import com.letsgo.appletsgo.domain.model.entity.Subcategories;
import com.letsgo.appletsgo.domain.model.entity.User;
import com.letsgo.appletsgo.presenter.ActividadesPresenter;
import com.letsgo.appletsgo.view.ActividadesView;
import com.letsgo.appletsgo.view.NearlyView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by louislopez on 14/02/17.
 */

public class HomeActivity extends BaseAppCompat implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, ActividadesView,
        NearlyView, LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{
    private static String TAG = "HomeActivity" ;

    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    private static final int REQUEST_CHECK_SETTINGS = 0x1;

    @BindView(R.id.famCalendar) FloatingActionMenu famCalendar;
    @BindView(R.id.rviEvents) RecyclerView rviEvents;
    @BindView(R.id.tviNoData) TextView tviNoData;
    @BindView(R.id.drawer_layout)   DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.iviLogo) ImageView iviLogo;
    @BindView(R.id.iviMenu) ImageView iviMenu;

    @BindView(R.id.rlaDistrito) RelativeLayout rlaDistrito;
    @BindView(R.id.rlaAllDistrito) RelativeLayout rlaAllDistrito;
    @BindView(R.id.rlaNearly) RelativeLayout rlaNearly;
    @BindView(R.id.tviFiltros) TextView tviFiltros;
    @BindView(R.id.tviAceptarFilterDistrito) TextView tviAceptarFilterDistrito;
    @BindView(R.id.tviFree) TextView tviFree;
    @BindView(R.id.tviDistrito) TextView tviDistrito;
    @BindView(R.id.tviCancelarDialogDistrito) TextView tviCancelarDialogDistrito;
    @BindView(R.id.rlaBtnDistrito) RelativeLayout rlaBtnDistrito;
    @BindView(R.id.rlaFilterAll) RelativeLayout rlaFilterAll;
    @BindView(R.id.viewLoading) ProgressBar viewLoading;

    @BindView(R.id.rlaFree) RelativeLayout rlaFree;

    @BindView(R.id.llaLinearFree) LinearLayout llaLinearFree;
    @BindView(R.id.llaLinearFilter) LinearLayout llaLinearFilter;
    @BindView(R.id.llaDistritoComponent) DistritoComponent llaDistritoComponent;
    @BindView(R.id.tviAllDistrito) TextView tviAllDistrito;
    @BindView(R.id.iviCheckAllDistrito) ImageView iviCheckAllDistrito;
    @BindView(R.id.iviCheckNearly) ImageView iviCheckNearly;
    @BindView(R.id.tviQuantityFilter) TextView tviQuantityFilter;
    @BindView(R.id.iviLike) ImageView iviLike;


    private ImageView iviPerfil;
    private TextView tviName;
    private EventAdapter eventAdapter;
    private List<Actividades> actividadesList = new ArrayList<>();
    private FusedLocationProviderApi fusedLocationProviderApi;
    private GoogleApiClient googleApiClient;
    private User user = new User();
    private ActividadesPresenter actividadesPresenter;
    private List<Distrito> listDistrito;
    private Distrito distrito;
    private String[] distritosArray;
    private SessionUser sessionUser = new SessionUser();
    private CategoriesToPreferences categoriesToPreferences;
    private ActividadesRaw actividadesRaw;
    private Actividades favorite;
    private boolean nearlySelected = false;
    private LocationRequest mLocationRequest;
    private Location mCurrentLocation;
    private NearlyView nearlyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        distritosArray = new String[]{"Cerca", "Todos los distritos", "Lima", "Lince", this.getString(R.string.jesus_maria), "San Isidro", "Miraflores", "Barranco", "San Borja"};
        createLocationRequest();
        initUI();
        initOnclickListenerViews(iviMenu, rlaFree, rlaFilterAll, rlaBtnDistrito, rlaDistrito, tviCancelarDialogDistrito, tviAceptarFilterDistrito, rlaAllDistrito, iviLike, rlaNearly);
        initViewEvents();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public void initUI(){
        nearlyView = this;
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        iviPerfil = (ImageView) headerView.findViewById(R.id.iviPerfil);
        tviName = (TextView) headerView.findViewById(R.id.tviName);
        fusedLocationProviderApi = LocationServices.FusedLocationApi;
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        //MenuItem itemloged =  navigationView.getMenu().getItem(navigationView.getMenu().size()-1);
        navigationView.setCheckedItem(R.id.nav_MeEvents);
        user = SessionUser.getSessionUser(this);

        Point deviceSize= ScreenUtils.getDeviceDimention(this);
        int deviceW= deviceSize.x;
        String url = "https://graph.facebook.com/" + user.getIdFacebook()+ "/picture?type=large&width="+deviceW;
        Picasso.with(getApplicationContext())
                .load(url)
                .resize(500,500)
                .placeholder(R.drawable.place_holder)
                .transform(new CircleTransform())
                .into(iviPerfil);
        tviName.setText(user.getName_complete());
        initPresenter();
        createCustomAnimation();
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private void createCustomAnimation() {
        AnimatorSet set = new AnimatorSet();

        ObjectAnimator scaleOutX = ObjectAnimator.ofFloat(famCalendar.getMenuIconView(), "scaleX", 1.0f, 0.2f);
        ObjectAnimator scaleOutY = ObjectAnimator.ofFloat(famCalendar.getMenuIconView(), "scaleY", 1.0f, 0.2f);

        ObjectAnimator scaleInX = ObjectAnimator.ofFloat(famCalendar.getMenuIconView(), "scaleX", 0.2f, 1.0f);
        ObjectAnimator scaleInY = ObjectAnimator.ofFloat(famCalendar.getMenuIconView(), "scaleY", 0.2f, 1.0f);

        scaleOutX.setDuration(50);
        scaleOutY.setDuration(50);

        scaleInX.setDuration(150);
        scaleInY.setDuration(150);

        scaleInX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                famCalendar.getMenuIconView().setImageResource(famCalendar.isOpened()
                        ? R.drawable.ic_close : R.drawable.school_calendar);
            }
        });

        set.play(scaleOutX).with(scaleOutY);
        set.play(scaleInX).with(scaleInY).after(scaleOutX);
        set.setInterpolator(new OvershootInterpolator(2));

        famCalendar.setIconToggleAnimatorSet(set);
    }

    public void llenarDisteito(){
      /*  this.distritoList = distritoList;
        llaDistritoComponent.init();*/
    }

    public void validateFiltroDistrito(){
        List<Distrito> distritoList = null;
        List<PlacesRaw> placesRawList = new ArrayList<>();
        distritoList = sessionUser.getDistrosUser(this).getDistritoList();
        if (distritoList != null) {
            List<Distrito> distritosSession = SessionUser.getDistrosUser(this).getDistritoList();
            List<filterPlacesRaw> filterPlacesRaws = new ArrayList<>();
            for (int i = 0; i < distritosSession.size(); i++) {
                if (distritosSession.get(i).isCheck()) {
                    filterPlacesRaw filterPlacesRaw = new filterPlacesRaw();
                    filterPlacesRaw.setId(distritosSession.get(i).getId_ubigeos());
                    filterPlacesRaw.setDescription(distritosSession.get(i).getDescription());
                    filterPlacesRaws.add(filterPlacesRaw);
                }
            }
            placesRawList = filterDistritos(filterPlacesRaws);

            if (filterPlacesRaws.size() == 1) {
                tviDistrito.setText(filterPlacesRaws.get(0).getDescription());
            } else
                tviDistrito.setText("Distrito");
            actividadesRaw.setFilterPlaces(placesRawList);
        } else{
            PlacesRaw placesRaw = new PlacesRaw();
            placesRawList.add(placesRaw);
            actividadesRaw.setFilterPlaces(placesRawList);
        }
        if(nearlySelected){
            actividadesRaw.setLatitude(String.valueOf(mCurrentLocation.getLatitude()));
            actividadesRaw.setLongitude(String.valueOf(mCurrentLocation.getLongitude()));
        } else{
            actividadesRaw.setLatitude(String.valueOf(""));
            actividadesRaw.setLongitude(String.valueOf(""));
        }
    }

    public void initPresenter(){
        actividadesPresenter = new ActividadesPresenter();
        actividadesPresenter.attachedView(this);
        actividadesPresenter.getCategoriesFromPreferences();
    }

    public void initViewEvents(){
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

    public List<PlacesRaw> filterDistritos(List<filterPlacesRaw> distritos){
        List<PlacesRaw> placesRawList = new ArrayList<>();
        for(filterPlacesRaw filterPlacesRaw : distritos){
            PlacesRaw placesRaw = new PlacesRaw();
            placesRaw.setId(Integer.parseInt(filterPlacesRaw.getId()));
            placesRawList.add(placesRaw);
        }
        return placesRawList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iviMenu:
                drawer.openDrawer(GravityCompat.START);
                break;
            case R.id.rlaFree:
                rviEvents.removeAllViews();
                actividadesList = new ArrayList<>();
                actividadesRaw.setFilterPrices("2");
                actividadesRaw.setFilterPublics(actividadesRaw.getFilterPublics());
                actividadesPresenter.listCatalog(actividadesRaw);

                llaLinearFree.setVisibility(View.VISIBLE);
                changeTextHeader(tviFree, 3);

                llaLinearFilter.setVisibility(View.INVISIBLE);
                changeTextHeader(tviFiltros, 1);

                break;
            case R.id.rlaFilterAll:
                if (llaLinearFilter.getVisibility()==View.VISIBLE){
                    nextActivity(FilterOptionActivity.class, true);
                } else {
                    rviEvents.removeAllViews();
                    actividadesList = new ArrayList<>();
                    actividadesRaw.setFilterPrices("3");
                    actividadesRaw.setFilterPublics(actividadesRaw.getFilterPublics());
                    actividadesPresenter.listCatalog(actividadesRaw);

                    llaLinearFree.setVisibility(View.INVISIBLE);
                    changeTextHeader(tviFree, 1);

                    llaLinearFilter.setVisibility(View.VISIBLE);
                    changeTextHeader(tviFiltros, 3);

                }
                break;
            case R.id.rlaBtnDistrito:
                if (rlaDistrito.getVisibility() == View.GONE)
                    rlaDistrito.setVisibility(View.VISIBLE);
                else
                    rlaDistrito.setVisibility(View.GONE);
                break;
            case R.id.tviCancelarDialogDistrito:
                rlaDistrito.setVisibility(View.GONE);
                break;
            case R.id.tviAceptarFilterDistrito:
                rlaDistrito.setVisibility(View.GONE);
                LogUtils.v("SESION HOME", SessionUser.getDistrosUser(this).toString());
                validateFiltroDistrito();
                actividadesPresenter.listCatalog(actividadesRaw);
                break;
            case R.id.rlaAllDistrito:
                iviCheckAllDistrito.setVisibility(View.VISIBLE);
                iviCheckNearly.setVisibility(View.GONE);
                llaDistritoComponent.removeAllViews();
                llaDistritoComponent.init(listDistrito, this, true, false, iviCheckAllDistrito, this);
                break;
            case R.id.rlaNearly:
                showSettingDialog();
                break;
            case R.id.iviLike:
                nextActivity(FavoritesActivity.class, true);
                break;

        }
    }



    private void changeTextHeader(TextView textView, int type){
        String font = "";
        try {
            switch (type){
                case 1: font = "fonts/Roboto-Light.ttf";break;
                case 2: font = "fonts/Roboto-Medium.ttf";break;
                case 3: font = "fonts/Roboto-Bold.ttf";break;
            }
        }catch (Exception e){
            font = "fonts/Roboto-Regular.ttf";
        }


        Typeface tf = Typeface.createFromAsset(this.getAssets(), font);
        textView.setTypeface(tf);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if(rlaDistrito.getVisibility() == View.VISIBLE){
            rlaDistrito.setVisibility(View.GONE);
        }else{
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_MeEvents) {
            LogUtils.v(TAG, " click menu activiades" );
            // Handle the camera action
        } else if (id == R.id.nav_perfil) {
            LogUtils.v(TAG, " click menu perfil" );
            nextActivity(MyPerfilActivity.class, true);
        } else if (id == R.id.nav_email) {
            nextActivity(ContactActivity.class, true);

        } else if (id == R.id.nav_logout) {
            LogUtils.v(TAG, " click Cerrar Sesion" );
            User user = new User();
            user.setIdFacebook("");
            SessionUser.saveSessionUser(getApplicationContext(), user);
            nextActivity(LoginActivity.class, false);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void getActividades(List<Actividades> tiendaList) {
        LogUtils.v(TAG, " Lista actividades" + tiendaList.toString() );
        actividadesList = tiendaList;
        actividadesPresenter.assignFavorites(this.actividadesList);
    }

    @Override
    public void detalleActividad(DetalleActividades detalleActividades) {
        tviNoData.setVisibility(View.GONE);
        rviEvents.setVisibility(View.VISIBLE);
    }

    @Override
    public void getDistritos(List<Distrito> distritoList) {
        LogUtils.v(TAG, " getDistritos::: " + distritoList.toString());
        listDistrito = new ArrayList<>();
        listDistrito = distritoList;
        llaDistritoComponent.removeAllViews();
        llaDistritoComponent.init(listDistrito, this, false, false, iviCheckAllDistrito, this);
        tviNoData.setVisibility(View.GONE);
        rviEvents.setVisibility(View.VISIBLE);
    }


    @Override
    public void showLoading() {
        viewLoading.setVisibility(View.VISIBLE);
        rviEvents.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        viewLoading.setVisibility(View.GONE);
        rviEvents.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyActivitys() {
        rviEvents.setVisibility(View.GONE);
        tviNoData.setVisibility(View.VISIBLE);

    }

    @Override
    public void getCategoriesFromPreferences(CategoriesToPreferences categoriesToPreferences) {
        this.categoriesToPreferences = categoriesToPreferences;
        actividadesRaw = generateActividadesRaw("", 0, "", "", "", "", "", "");
        validateFiltroDistrito();
        actividadesRaw.setFilterPrices("3");
        actividadesRaw.setFilterPublics(actividadesRaw.getFilterPublics());
        actividadesPresenter.listCatalog(actividadesRaw);
        actividadesPresenter.distritosDisponibles();
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
        this.favorite = actividades;
        actividadesPresenter.deleteFavorite(actividadId);
    }

    @Override
    public void assignFavorites(List<Actividades> actividadesList) {
        this.actividadesList = actividadesList;
        eventAdapter = new EventAdapter(this, this.actividadesList, this);
        rviEvents.setAdapter(eventAdapter);
        tviNoData.setVisibility(View.GONE);
        rviEvents.setVisibility(View.VISIBLE);
    }

    @Override
    public void saveFavoriteSuccess() {
        this.favorite.setFavorite(true);
        eventAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteFavoriteSuccess() {
        this.favorite.setFavorite(false);
        eventAdapter.notifyDataSetChanged();
    }

    @Override
    public Context getContext() {
        return this;
    }

    private ActividadesRaw generateActividadesRaw(String free,
                                                  int dateDays, String date_since, String date_until,
                                                  String latitude, String longitude, String quantity,
                                                  String from){
        ActividadesRaw actividadesRaw = new ActividadesRaw();
        actividadesRaw.setFilterPublics(String.valueOf(categoriesToPreferences.getPublicType()));
        List<Categories> categoriesList = categoriesToPreferences.getCategoriesList();
        tviQuantityFilter.setText(String.valueOf(categoriesList.size()));
        List<CategoriesRaw> categoriesRawList = new ArrayList<>();
        for(Categories categories : categoriesList){
            CategoriesRaw categoriesRaw = new CategoriesRaw();
            categoriesRaw.setId(categories.getId_activities_types());
            List<Subcategories> subcategoriesList = categories.getSubcategoriesList();
            List<SubcategoriesRaw> subcategoriesRawList = new ArrayList<>();
            if(subcategoriesList != null){
                for(Subcategories subcategories : subcategoriesList){
                    if(subcategories.getId_activities_subtypes() == 0){
                        subcategoriesList.add(new Subcategories());
                        categoriesRaw.setFilterSubtypes(subcategoriesRawList);
                    } else{
                        SubcategoriesRaw subcategoriesRaw = new SubcategoriesRaw();
                        subcategoriesRaw.setId(subcategories.getId_activities_subtypes());
                        subcategoriesRawList.add(subcategoriesRaw);
                    }
                }
            } else{
                subcategoriesList.add(new Subcategories());
                categoriesRaw.setFilterSubtypes(subcategoriesRawList);
            }
            categoriesRawList.add(categoriesRaw);
        }
        actividadesRaw.setFilterTypes(categoriesRawList);
        actividadesRaw.setFilterPrices(free);
        actividadesRaw.setDate_days(dateDays);
        actividadesRaw.setDate_since(date_since);
        actividadesRaw.setDate_until(date_until);
        actividadesRaw.setLatitude(latitude);
        actividadesRaw.setLongitude(longitude);
        actividadesRaw.setQuantity(quantity);
        actividadesRaw.setFrom(from);
        return actividadesRaw;
    }

    @Override
    public void nearlySelected(boolean nearly) {
        nearlySelected = nearly;
    }

    @Override
    public void onConnected(Bundle bundle) {
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
    }

    protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, mLocationRequest, this);
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
    }

    private void showSettingDialog() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);//Setting priotity of Location request to high
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);//5 sec Time interval for location update
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true); //this is the key ingredient to show dialog always when GPS is off

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                        iviCheckNearly.setVisibility(View.VISIBLE);
                        iviCheckAllDistrito.setVisibility(View.GONE);
                        llaDistritoComponent.removeAllViews();
                        llaDistritoComponent.init(listDistrito, HomeActivity.this, false, true, iviCheckNearly, nearlyView);
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(HomeActivity.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case RESULT_OK:
                        iviCheckNearly.setVisibility(View.VISIBLE);
                        iviCheckAllDistrito.setVisibility(View.GONE);
                        llaDistritoComponent.removeAllViews();
                        llaDistritoComponent.init(listDistrito, HomeActivity.this, false, true, iviCheckNearly, nearlyView);
                        break;
                    case RESULT_CANCELED:
                        break;
                }
                break;
        }
    }
}
