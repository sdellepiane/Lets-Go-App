package com.letsgo.appletsgo.app.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.core.BaseAppCompat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterOptionActivity extends BaseAppCompat {
    @BindView(R.id.iviBack)
    ImageView iviBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_option);
        ButterKnife.bind(this);

        initOnclickListenerViews(iviBack);
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
