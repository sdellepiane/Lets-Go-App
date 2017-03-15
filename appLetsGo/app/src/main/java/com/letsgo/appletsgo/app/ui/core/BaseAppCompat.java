package com.letsgo.appletsgo.app.ui.core;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.letsgo.appletsgo.app.utils.svgGlide.SvgDecoder;
import com.letsgo.appletsgo.app.utils.svgGlide.SvgDrawableTranscoder;
import com.letsgo.appletsgo.app.utils.svgGlide.SvgSoftwareLayerSetter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by louislopez on 13/02/17.
 */

public class BaseAppCompat extends AppCompatActivity implements View.OnClickListener{
    private ProgressDialog mProgressDialog;
    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;

    public BaseAppCompat() {
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        //MultiDex.install(this);
    }

    private void bindViews() {
        ButterKnife.bind(this);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void nextActivity(Class<?> activity) {
        this.nextActivity(activity, false);
    }

    protected void nextData(Class<?> activity, Bundle bundle) {
        this.nextData(activity, bundle, false);
    }

    protected void nextActivity(Class<?> activity, boolean notDestroy) {
        this.startActivity(new Intent(this, activity));
        if(!notDestroy) {
            this.finish();
        }

    }

    protected void resultActivity(Class<?> activity, int code){
        this.startActivityForResult(new Intent(this, activity),code);
    }

    protected void resultActivityData(Class<?> activity, int code, Bundle bundle){
        Intent intent = new Intent(this, activity);
        intent.putExtras(bundle);
        this.startActivityForResult(intent,code);
    }

    protected void nextData(Class<?> activity, Bundle bundle, boolean notDestroy) {
        Intent intent = new Intent(this, activity);
        intent.putExtras(bundle);
        this.startActivity(intent);
        if(!notDestroy) {
            this.finish();
        }

    }

    protected void showActionbar(boolean mstate) {
        if(mstate) {
            this.getSupportActionBar().show();
        } else {
            this.getSupportActionBar().hide();
        }

    }

    protected void startCustomActionBar(int mCustomView) {
        ActionBar mActionBar = this.getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        this.showActionbar(true);
    }

    public void setCustomFont(TextView txtview, String font, int style) {
        Typeface typefaceFont = Typeface.createFromAsset(this.getAssets(), font);
        if(style == -1) {
            txtview.setTypeface(typefaceFont);
        } else {
            txtview.setTypeface(typefaceFont, style);
        }
    }

    protected void iconSVG(String uriSVG, ImageView imageView){
        requestBuilder = Glide.with(this)
                .using(Glide.buildStreamModelLoader(Uri.class, this), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                // .error(R.drawable.loadimage_catalog)
                .animate(android.R.anim.fade_in)
                .listener(new SvgSoftwareLayerSetter<Uri>());
        requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                // SVG cannot be serialized so it's not worth to cache it
                // and the getResources() should be fast enough when acquiring the InputStream
                .load(Uri.parse(uriSVG))
                .into(imageView);
    }

    public void snackBar(String msj, View rlaContent){
       /* Snackbar snackbar = Snackbar
                .make(rlaContent, msj, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.title_dialog_color_fail));
        snackbar.show();*/

    }

    public void snackBarSucceso(String msj, View rlaContent){
       /* Snackbar snackbar = Snackbar
                .make(rlaContent, msj, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.new_verder));
        snackbar.show();*/

    }

    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }


    //DIALOG PROGRESS
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading…");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

/*    public String IDSession(){
        if(SessionManager.getInstance(this).isLogged())
            return SessionManager.getInstance(this).getUserLogged().getId_users();
        else
            return SessionManager.getInstance(this).getAndroidId();
    }*/


    public void initOnclickListenerViewsGrpus(ViewGroup... viewGroups){
        for (ViewGroup viewGroup : viewGroups)
            viewGroup.setOnClickListener(this);
    }

    public void initOnclickListenerViews(View... views){
        for (View view : views)
            view.setOnClickListener(this);
    }

    public void fadeAnimation(Activity a)
    {
       // a.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    @Override
    public void onClick(View v) {

    }

    public String getVersionApp(){
        String version = "";
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }



}


