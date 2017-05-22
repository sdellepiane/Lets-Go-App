package com.letsgo.appletsgo.app.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.adapter.FavoriteAdapter;
import com.letsgo.appletsgo.app.ui.core.BaseAppCompat;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.presenter.FavoritesPresenter;
import com.letsgo.appletsgo.view.FavoritesView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FavoritesActivity extends BaseAppCompat implements FavoritesView{

    @BindView(R.id.rviFavorites) RecyclerView rviFavorites;
    @BindView(R.id.tviNoData) TextView tviNoData;
    @BindView(R.id.vieLoading) View vieLoading;
    @BindView(R.id.iviBack) ImageView iviBack;

    private FavoritesPresenter favoritesPresenter;
    private FavoriteAdapter favoriteAdapter;
    private List<Actividades> favorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        ButterKnife.bind(this);
        initRecyclerView();
        initPresenter();
    }

    public void initPresenter(){
        favoritesPresenter = new FavoritesPresenter();
        favoritesPresenter.attachedView(this);
        favoritesPresenter.getFavorites();
    }

    private void initRecyclerView(){
        rviFavorites.setHasFixedSize(true);
        rviFavorites.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoading() {
        vieLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        vieLoading.setVisibility(View.GONE);
    }

    @Override
    public void showFavorites(List<Actividades> favorites) {
        this.favorites = favorites;
        favoriteAdapter = new FavoriteAdapter(this, this.favorites);
        rviFavorites.setAdapter(favoriteAdapter);
        tviNoData.setVisibility(View.GONE);
        rviFavorites.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.iviBack)
    public void back(){
        finish();
    }
}
