package com.letsgo.appletsgo.app.ui.activity;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.core.BaseAppCompat;
import com.letsgo.appletsgo.app.utils.CircleTransform;
import com.letsgo.appletsgo.app.utils.ScreenUtils;
import com.letsgo.appletsgo.data.store.SessionUser;
import com.letsgo.appletsgo.domain.model.entity.User;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyPerfilActivity extends BaseAppCompat {
    @BindView(R.id.iviBack) ImageView iviBack;
    @BindView(R.id.iviPerfil) ImageView iviPerfil;
    @BindView(R.id.tviFirstName) TextView tviFirstName;
    @BindView(R.id.tviLastName) TextView tviLastName;
    @BindView(R.id.tviEmail) TextView tviEmail;
    @BindView(R.id.tviNameCompleto) TextView tviNameCompleto;

    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_perfil);
        ButterKnife.bind(this);

        initOnclickListenerViews(iviBack);

        user = SessionUser.getSessionUser(this);
        tviFirstName.setText(user.getFirst_name());
        tviLastName.setText(user.getLast_name());
        tviEmail.setText(user.getEmail());
        tviNameCompleto.setText(user.getName_complete());

        Point deviceSize= ScreenUtils.getDeviceDimention(this);
        int deviceW= deviceSize.x;
        String url = "https://graph.facebook.com/" + SessionUser.getSessionUser(this.getApplicationContext()).getIdFacebook()+ "/picture?type=large&width="+deviceW;
        Picasso.with(getApplicationContext())
                .load(url)
                .resize(500,500)
                .placeholder(R.drawable.place_holder)
                .transform(new CircleTransform())
                .into(iviPerfil);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iviBack:
                onBackPressed();
                break;
        }
    }
}
