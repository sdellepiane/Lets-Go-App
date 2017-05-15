package com.letsgo.appletsgo.app.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
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
import com.letsgo.appletsgo.data.entity.raw.filterPlacesRaw;
import com.letsgo.appletsgo.data.store.SessionUser;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.model.entity.DetalleActividades;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.domain.model.entity.DistritosSession;
import com.letsgo.appletsgo.domain.model.entity.User;
import com.letsgo.appletsgo.presenter.ActividadesPresenter;
import com.letsgo.appletsgo.view.ActividadesView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by louislopez on 14/02/17.
 */

public class HomeActivity extends BaseAppCompat implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, ActividadesView{
    private static String TAG = "HomeActivity" ;

    @BindView(R.id.famCalendar) FloatingActionMenu famCalendar;
    @BindView(R.id.rviEvents) RecyclerView rviEvents;
    @BindView(R.id.tviNoData) TextView tviNoData;
    @BindView(R.id.drawer_layout)   DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.iviLogo) ImageView iviLogo;
    @BindView(R.id.iviMenu) ImageView iviMenu;

    @BindView(R.id.rlaDistrito) RelativeLayout rlaDistrito;
    @BindView(R.id.rlaAllDistrito) RelativeLayout rlaAllDistrito;
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
    @BindView(R.id.iviSearch) ImageView iviSearch;


    ImageView iviPerfil;
    TextView tviName;
    private EventAdapter eventAdapter;
    private List<Actividades> actividadesList = new ArrayList<>();


    private User user = new User();
    private ActividadesPresenter actividadesPresenter;
    List<Distrito> listDistrito;
    Distrito distrito;
    String[] distritosArray;
    SessionUser sessionUser = new SessionUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        distritosArray = new String[]{"Todos los distritos", "Lima", "Lince", this.getString(R.string.jesus_maria), "San Isidro", "Miraflores", "Barranco", "San Borja"};

        initPresenter();
        initUI();
        initOnclickListenerViews(iviMenu, rlaFree, rlaFilterAll, rlaBtnDistrito, rlaDistrito, tviCancelarDialogDistrito, tviAceptarFilterDistrito, rlaAllDistrito);
        initViewEvents();



    }

    public void initUI(){
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        iviPerfil = (ImageView) headerView.findViewById(R.id.iviPerfil);
        tviName = (TextView) headerView.findViewById(R.id.tviName);
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

        validateFiltroDistrito();
        initPresenter();
        createCustomAnimation();
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
            filterDistritos(filterPlacesRaws);

            if (filterPlacesRaws.size() == 1) {
                tviDistrito.setText(filterPlacesRaws.get(0).getDescription());
            } else
                tviDistrito.setText("Distrito");

        }
    }

    public void initPresenter(){
        actividadesPresenter = new ActividadesPresenter();
        actividadesPresenter.attachedView(this);

        ActividadesRaw raw = new ActividadesRaw();
        raw.setFilterPublics("1");
        actividadesPresenter.listCatalog(raw);
        actividadesPresenter.distritosDisponibles();

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

    public void filterDistritos(List<filterPlacesRaw> distritos){
        rviEvents.removeAllViews();
        actividadesList = new ArrayList<>();
        ActividadesRaw raw = new ActividadesRaw();
        raw.setFilterPublics("1");
        raw.setFilterFree("0");
        raw.setFilterPlaces(distritos);


        actividadesPresenter.listCatalog(raw);
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
                ActividadesRaw raw = new ActividadesRaw();
                raw.setFilterPublics("1");
                raw.setFilterFree("1");
                actividadesPresenter.listCatalog(raw);

                llaLinearFree.setVisibility(View.VISIBLE);
                changeTextHeader(tviFree, 3);

                llaLinearFilter.setVisibility(View.INVISIBLE);
                changeTextHeader(tviFiltros, 1);

                break;
            case R.id.rlaFilterAll:
                if (llaLinearFilter.getVisibility()==View.VISIBLE){
                    nextActivity(FilterOptionActivity.class, true);
                }else {
                    rviEvents.removeAllViews();
                    actividadesList = new ArrayList<>();
                    ActividadesRaw raw2 = new ActividadesRaw();
                    raw2.setFilterPublics("1");
                    raw2.setFilterFree("0");
                    actividadesPresenter.listCatalog(raw2);

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
                break;
            case R.id.rlaAllDistrito:
                iviCheckAllDistrito.setVisibility(View.VISIBLE);
                llaDistritoComponent.removeAllViews();
                llaDistritoComponent.init(listDistrito, this, true, iviCheckAllDistrito);
                break;

        }
    }

    @OnClick(R.id.iviSearch)
    public void touchSearch(){
        nextActivity(SearchActivity.class, true);
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
        eventAdapter = new EventAdapter(this, actividadesList);
        rviEvents.setAdapter(eventAdapter);

    }

    @Override
    public void detalleActividad(DetalleActividades detalleActividades) {

    }

    @Override
    public void getDistritos(List<Distrito> distritoList) {
        LogUtils.v(TAG, " getDistritos::: " + distritoList.toString());
        listDistrito = new ArrayList<>();
        listDistrito = distritoList;
        llaDistritoComponent.removeAllViews();
        llaDistritoComponent.init(listDistrito, this, false, iviCheckAllDistrito);

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
    public void showMessageError(String message) {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
