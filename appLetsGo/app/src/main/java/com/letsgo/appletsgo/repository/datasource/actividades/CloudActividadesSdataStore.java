package com.letsgo.appletsgo.repository.datasource.actividades;

import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.DetalleActividadRaw;
import com.letsgo.appletsgo.data.entity.response.ActividadesResponse;
import com.letsgo.appletsgo.data.entity.response.DetalleActividadResponse;
import com.letsgo.appletsgo.data.entity.response.DistritosResponse;
import com.letsgo.appletsgo.data.rest.ApiClient;
import com.letsgo.appletsgo.repository.actividades.RepositoryCallBackActividades;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by louislopez on 4/03/17.
 */

public class CloudActividadesSdataStore implements ActividadesServiceDataStore {
    private static String TAG = "CloudActividadesSdataStore : ";
    private final ApiClient restApi;

    public CloudActividadesSdataStore(ApiClient restApi) {
        this.restApi = restApi;
    }

    @Override
    public void getActividades(ActividadesRaw raw, final RepositoryCallBackActividades repositoryCallBackActividades) {
        Call<ActividadesResponse> call = restApi.getLetsGoTokenInterface().getActividades(raw);
        call.enqueue(new Callback<ActividadesResponse>() {
            @Override
            public void onResponse(Call<ActividadesResponse> call, Response<ActividadesResponse> response) {
                ActividadesResponse actividadesResponse = response.body();
                if (actividadesResponse != null)repositoryCallBackActividades.onSuccess(actividadesResponse);
                else repositoryCallBackActividades.onFailure(null);
            }

            @Override
            public void onFailure(Call<ActividadesResponse> call, Throwable t) {
                repositoryCallBackActividades.onFailure(t);
            }
        });
    }

    @Override
    public void getDetailActividad(DetalleActividadRaw raw, final RepositoryCallBackActividades repositoryCallBackActividades) {
        Call<DetalleActividadResponse> call = restApi.getLetsGoTokenInterface().getDetailActividad(raw);
        call.enqueue(new Callback<DetalleActividadResponse>() {
            @Override
            public void onResponse(Call<DetalleActividadResponse> call, Response<DetalleActividadResponse> response) {
                DetalleActividadResponse detalleActividadResponse = response.body();
                if (detalleActividadResponse != null)repositoryCallBackActividades.onSuccess(detalleActividadResponse);
                else repositoryCallBackActividades.onFailure(null);
            }

            @Override
            public void onFailure(Call<DetalleActividadResponse> call, Throwable t) {
                repositoryCallBackActividades.onFailure(t);
            }
        });
    }

    @Override
    public void getDistritos(final RepositoryCallBackActividades repositoryCallBackActividades) {
        Call<DistritosResponse> call = restApi.getLetsGoTokenInterface().getDistritos();
        call.enqueue(new Callback<DistritosResponse>() {
            @Override
            public void onResponse(Call<DistritosResponse> call, Response<DistritosResponse> response) {
                DistritosResponse distritosResponse = response.body();
                if (distritosResponse != null) repositoryCallBackActividades.onSuccess(distritosResponse);
                else repositoryCallBackActividades.onFailure(null);
            }

            @Override
            public void onFailure(Call<DistritosResponse> call, Throwable t) {
                repositoryCallBackActividades.onFailure(t);
            }
        });
    }
}
