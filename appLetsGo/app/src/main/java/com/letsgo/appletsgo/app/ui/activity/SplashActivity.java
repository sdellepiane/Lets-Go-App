package com.letsgo.appletsgo.app.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.core.BaseAppCompat;
import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.data.store.SessionUser;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.domain.model.entity.DistritosSession;
import com.letsgo.appletsgo.domain.model.entity.User;
import com.letsgo.appletsgo.presenter.SplashPresenter;
import com.letsgo.appletsgo.view.SplashView;

import net.hockeyapp.android.CrashManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends BaseAppCompat implements SplashView{
    private final static int PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 1000;
    User user = new User();
    SplashPresenter splashPresenter;
    private int permissionCheck;
    private int typeRequestLocation = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_splash);
        List<Distrito> distritoList = new ArrayList<>();
        DistritosSession distritosSession = new DistritosSession();
        distritosSession.setDistritoList(distritoList);
        SessionUser.saveDistrosUser(this, distritosSession);
        splashPresenter = new SplashPresenter();
        splashPresenter.attachedView(this);
        permissionCheck = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "your.package",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        user = SessionUser.getSessionUser(this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    if (user != null && !user.getIdFacebook().equals("")){
                        typeRequestLocation = 1;
                        splashPresenter.getCategoriesFromPreferences();
                    } else{
                        typeRequestLocation = 0;
                        nextActivity(LoginActivity.class, false);
                    }
                } else{
                    ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_ACCESS_FINE_LOCATION);
                }

            }
        }, 500);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkForCrashes();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void evaluateCategoriesFromPreferences(boolean saved) {
        if(saved){
            typeRequestLocation = 1;
            nextActivity(HomeActivity.class, false);
        } else{
            typeRequestLocation = 2;
            nextActivity(FilterFirstActivity.class, false);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case PERMISSION_REQUEST_ACCESS_FINE_LOCATION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    switch(typeRequestLocation){
                        case 0:
                            nextActivity(LoginActivity.class, false);
                            break;
                        case 1:
                            nextActivity(HomeActivity.class, false);
                            break;
                        case 2:
                            nextActivity(FilterFirstActivity.class, false);
                            break;

                    }
                }
                break;
        }
    }

    private void checkForCrashes() {
        CrashManager.register(this);
    }
}
