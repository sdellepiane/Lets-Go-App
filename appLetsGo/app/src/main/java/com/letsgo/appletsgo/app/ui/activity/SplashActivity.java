package com.letsgo.appletsgo.app.ui.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Handler;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends BaseAppCompat implements SplashView{
    User user = new User();
    SplashPresenter splashPresenter;

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
                if (user != null && !user.getIdFacebook().equals("")){
                    splashPresenter.getCategoriesFromPreferences();
                }else{
                    nextActivity(LoginActivity.class, false);
                }

            }
        }, 500);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void evaluateCategoriesFromPreferences(boolean saved) {
        if(saved){
            nextActivity(HomeActivity.class, false);
        } else{
            nextActivity(FilterFirstActivity.class, false);
        }
    }
}
