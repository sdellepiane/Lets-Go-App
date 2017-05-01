package com.letsgo.appletsgo.repository.datasource.categories;

import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.DetalleActividadRaw;
import com.letsgo.appletsgo.data.entity.response.ActividadesResponse;
import com.letsgo.appletsgo.data.entity.response.CategoriesResponse;
import com.letsgo.appletsgo.data.entity.response.DetalleActividadResponse;
import com.letsgo.appletsgo.data.entity.response.DistritosResponse;
import com.letsgo.appletsgo.data.rest.ApiClient;
import com.letsgo.appletsgo.repository.Categories.RepositoryCallBackCategories;
import com.letsgo.appletsgo.repository.actividades.RepositoryCallBackActividades;
import com.letsgo.appletsgo.repository.datasource.actividades.ActividadesServiceDataStore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by louislopez on 4/03/17.
 */

public class CloudCategoriesDataStore implements CategoriesServiceDataStore {
    private static String TAG = "CloudCategoriesDataStore : ";
    private final ApiClient restApi;

    public CloudCategoriesDataStore(ApiClient restApi) {
        this.restApi = restApi;
    }

    @Override
    public void getCategories(final RepositoryCallBackCategories repositoryCallBackCategories) {
        Call<CategoriesResponse> call = restApi.getLetsGoTokenInterface().getCategories();
        call.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                CategoriesResponse categoriesResponse = response.body();
                if (categoriesResponse != null)repositoryCallBackCategories.onSuccess(categoriesResponse);
                else repositoryCallBackCategories.onFailure(null);
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                repositoryCallBackCategories.onFailure(t);
            }
        });
    }

    @Override
    public void saveCategoriesToPreferences(RepositoryCallBackCategories repositoryCallBackCategories) {

    }
}
