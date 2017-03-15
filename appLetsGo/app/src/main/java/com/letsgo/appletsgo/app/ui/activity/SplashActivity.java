package com.letsgo.appletsgo.app.ui.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends BaseAppCompat {
    User user = new User();
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

        user = SessionUser.getSessionUser(this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (user != null && !user.getIdFacebook().equals("")){
                    nextActivity(FilterFirstActivity.class, false);
                }else{
                    nextActivity(LoginActivity.class, false);
                }

            }
        }, 500);
    }
}
