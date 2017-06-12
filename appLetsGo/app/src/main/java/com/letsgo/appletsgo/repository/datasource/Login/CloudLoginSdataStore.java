package com.letsgo.appletsgo.repository.datasource.Login;

import com.letsgo.appletsgo.data.entity.CompleteUserResponse;
import com.letsgo.appletsgo.data.entity.raw.CompleteUserRaw;
import com.letsgo.appletsgo.data.entity.raw.LoginRaw;
import com.letsgo.appletsgo.data.entity.response.DistritosResponse;
import com.letsgo.appletsgo.data.entity.response.LoginResponse;
import com.letsgo.appletsgo.data.rest.ApiClient;
import com.letsgo.appletsgo.domain.model.entity.CompleteUser;
import com.letsgo.appletsgo.repository.Login.RepositoryCallBackLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by louislopez on 3/06/17.
 */

public class CloudLoginSdataStore implements LoginServiceDataStore{
    private static String TAG = "CloudLoginSdataStore";
    private final ApiClient restApi;

    public CloudLoginSdataStore(ApiClient restApi) {
        this.restApi = restApi;
    }


    @Override
    public void setLogin(LoginRaw raw, final RepositoryCallBackLogin repositoryCallBackLogin) {
        Call<LoginResponse> call = restApi.getLetsGoTokenInterface().loginWithFacebook(raw);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (loginResponse != null) repositoryCallBackLogin.onSuccess(loginResponse);
                else repositoryCallBackLogin.onFailure(null);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                repositoryCallBackLogin.onFailure(t);
            }
        });
    }

    @Override
    public void setCompleteLogin(CompleteUserRaw raw, final RepositoryCallBackLogin repositoryCallBackLogin) {
        Call<CompleteUserResponse> call = restApi.getLetsGoTokenInterface().completeLoginRegiste(raw);
        call.enqueue(new Callback<CompleteUserResponse>() {
            @Override
            public void onResponse(Call<CompleteUserResponse> call, Response<CompleteUserResponse> response) {
                CompleteUserResponse completeUserResponse = response.body();
                if (completeUserResponse != null) repositoryCallBackLogin.onSuccess(completeUserResponse);
                else repositoryCallBackLogin.onSuccess(null);
            }

            @Override
            public void onFailure(Call<CompleteUserResponse> call, Throwable t) {
                repositoryCallBackLogin.onFailure(t);
            }
        });
    }

    @Override
    public void distritos(final RepositoryCallBackLogin repositoryCallBackLogin) {
        Call<DistritosResponse> call = restApi.getLetsGoTokenInterface().ubigeoLima();
        call.enqueue(new Callback<DistritosResponse>() {
            @Override
            public void onResponse(Call<DistritosResponse> call, Response<DistritosResponse> response) {
                DistritosResponse distritosResponse = response.body();
                if (distritosResponse != null) repositoryCallBackLogin.onSuccess(distritosResponse);
                else repositoryCallBackLogin.onSuccess(null);
            }

            @Override
            public void onFailure(Call<DistritosResponse> call, Throwable t) {

            }
        });
    }
}
