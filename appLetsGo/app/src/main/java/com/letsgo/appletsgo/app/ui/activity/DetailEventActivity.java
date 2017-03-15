package com.letsgo.appletsgo.app.ui.activity;

import android.graphics.Typeface;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.core.BaseAppCompat;
import com.letsgo.appletsgo.app.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailEventActivity extends BaseAppCompat implements AppBarLayout.OnOffsetChangedListener{

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;


    //@BindView(R.id.backdrop) ImageView backdrop;
    @BindView(R.id.iviBack) ImageView iviBack;
    @BindView(R.id.mAppBarLayout) AppBarLayout mAppBarLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.iviBackTop) ImageView iviBackTop;
    @BindView(R.id.mTitle) TextView mTitle;
    @BindView(R.id.mTitleContainer) LinearLayout mTitleContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        ButterKnife.bind(this);

      /*  setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

       /* final Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Regular.ttf");
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Dia mundial del teatro");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTypeface(tf);*/

        //MenuItem itemloged =  navigationView.getMenu().getItem(navigationView.getMenu().size()-1);

        initOnclickListenerViews(iviBackTop);
        toolbar.setVisibility(View.GONE);
        mAppBarLayout.addOnOffsetChangedListener(this);
        toolbar.inflateMenu(R.menu.menu_detail);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);
        startAlphaAnimation(iviBack, 0, View.INVISIBLE);



        //loadBackdrop();
    }

    private void loadBackdrop() {
      /*  Picasso.with(this)
                .load(R.drawable.banner_test2)
                .into(backdrop);*/
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iviBackTop:
                onBackPressed();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if(!mIsTheTitleVisible) {
                toolbar.setVisibility(View.VISIBLE);
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                startAlphaAnimation(iviBack, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                toolbar.setBackgroundResource(R.color.colorPrimary);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                toolbar.setVisibility(View.GONE);
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                startAlphaAnimation(iviBack, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                toolbar.setBackgroundResource(R.color.white);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }
}
