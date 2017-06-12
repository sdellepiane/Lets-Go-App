package com.letsgo.appletsgo.repository.Login;

import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.data.TypeService;
import com.letsgo.appletsgo.data.entity.CompleteUserResponse;
import com.letsgo.appletsgo.data.entity.raw.CompleteUserRaw;
import com.letsgo.appletsgo.data.entity.raw.LoginRaw;
import com.letsgo.appletsgo.data.entity.response.DistritosResponse;
import com.letsgo.appletsgo.data.entity.response.LoginResponse;
import com.letsgo.appletsgo.data.mapper.LoginDataMapper;
import com.letsgo.appletsgo.domain.model.entity.CompleteUser;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.domain.model.entity.Login;
import com.letsgo.appletsgo.domain.repository.LoginServiceRepository;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackLogin;
import com.letsgo.appletsgo.repository.datasource.Login.LoginDataStoreFactory;
import com.letsgo.appletsgo.repository.datasource.Login.LoginServiceDataStore;

import java.util.List;

/**
 * Created by louislopez on 3/06/17.
 */

public class LoginDataRepository implements LoginServiceRepository{
    private static final String TAG = "LoginDataRepository: ";
    private LoginDataStoreFactory storeFactory;
    private LoginDataMapper dataMapper;

    public LoginDataRepository(LoginDataStoreFactory storeFactory, LoginDataMapper dataMapper) {
        this.storeFactory = storeFactory;
        this.dataMapper = dataMapper;
    }

    @Override
    public void setLoginReuqest(LoginRaw raw, final RequestCallBackLogin requestCallBackLogin) {
        final LoginServiceDataStore serviceDataStore = (LoginServiceDataStore) this.storeFactory.create(LoginDataStoreFactory.CLOUD);
        serviceDataStore.setLogin(raw, new RepositoryCallBackLogin() {
            @Override
            public void onSuccess(Object object) {
                Login login = dataMapper.transFormLoginWalkingService((LoginResponse) object);
                LogUtils.v(TAG, login.toString());
                requestCallBackLogin.onRequestSuccess(login, TypeService.LOGIN_FACEBOOK);
            }

            @Override
            public void onSuccess(Object response, Object header) {

            }

            @Override
            public void onFailure(Throwable throwable) {
                requestCallBackLogin.onRequestSuccess(throwable);
            }
        });

    }

    @Override
    public void setCompleteUserReuqest(CompleteUserRaw raw, final RequestCallBackLogin requestCallBackLogin) {
        final LoginServiceDataStore serviceDataStore = (LoginServiceDataStore) this.storeFactory.create(LoginDataStoreFactory.CLOUD);
        serviceDataStore.setCompleteLogin(raw, new RepositoryCallBackLogin() {
            @Override
            public void onSuccess(Object object) {
                CompleteUser completeUser = dataMapper.transFormCompleteUserWalkingService((CompleteUserResponse) object);
                LogUtils.v(TAG, completeUser.toString());
                requestCallBackLogin.onRequestSuccess(completeUser, TypeService.COMPLETE_LOGIN);
            }

            @Override
            public void onSuccess(Object response, Object header) {

            }

            @Override
            public void onFailure(Throwable throwable) {
                requestCallBackLogin.onRequestSuccess(throwable);
            }
        });
    }

    @Override
    public void distritos(final RequestCallBackLogin requestCallBackLogin) {
        final LoginServiceDataStore serviceDataStore = (LoginServiceDataStore) this.storeFactory.create(LoginDataStoreFactory.CLOUD);
        serviceDataStore.distritos(new RepositoryCallBackLogin() {
            @Override
            public void onSuccess(Object object) {
                List<Distrito> distritoList = dataMapper.transFormDistritoWAlkingService((DistritosResponse) object);
                LogUtils.v(TAG, distritoList.toString());
                requestCallBackLogin.onRequestSuccess(distritoList, TypeService.DISTRITOS);

            }

            @Override
            public void onSuccess(Object response, Object header) {

            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

}
