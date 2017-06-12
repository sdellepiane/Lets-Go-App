package com.letsgo.appletsgo.domain.interactor;

import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.data.entity.raw.CompleteUserRaw;
import com.letsgo.appletsgo.data.entity.raw.LoginRaw;
import com.letsgo.appletsgo.domain.model.entity.CompleteUser;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.domain.model.entity.Login;
import com.letsgo.appletsgo.domain.repository.LoginServiceRepository;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackLogin;

import java.util.List;

/**
 * Created by louislopez on 4/06/17.
 */

public class LoginInteractor {
    private static final String TAG = "LoginInteractor";
    private LoginServiceRepository serviceRepository;

    public LoginInteractor(LoginServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public void setLogin(LoginRaw raw, final RequestCallBackLogin requestCallBackLogin){
        this.serviceRepository.setLoginReuqest(raw, new RequestCallBackLogin() {
            @Override
            public void onRequestSuccess(Object object) {


            }

            @Override
            public void onRequestSuccess(Object object, int type) {
                Login login = (Login) object;
                LogUtils.v(TAG, "tiendasList " + login.toString());
                requestCallBackLogin.onRequestSuccess(login, type);
            }

            @Override
            public void onRequestFailure(Throwable e) {
                requestCallBackLogin.onRequestFailure(e);
            }
        });
    }

    public void setCompleteUserRegister(CompleteUserRaw raw, final RequestCallBackLogin requestCallBackLogin){
        this.serviceRepository.setCompleteUserReuqest(raw, new RequestCallBackLogin() {
            @Override
            public void onRequestSuccess(Object object) {

            }

            @Override
            public void onRequestSuccess(Object object, int type) {
                CompleteUser completeUser = (CompleteUser) object;
                LogUtils.v(TAG, "completeUser " + completeUser.toString());
                requestCallBackLogin.onRequestSuccess(completeUser, type);
            }

            @Override
            public void onRequestFailure(Throwable e) {
                requestCallBackLogin.onRequestFailure(e);
            }
        });
    }

    public void getDistritos(final RequestCallBackLogin requestCallBackLogin){
        this.serviceRepository.distritos(new RequestCallBackLogin() {
            @Override
            public void onRequestSuccess(Object object) {

            }

            @Override
            public void onRequestSuccess(Object object, int type) {
                List<Distrito> distritoList = ( List<Distrito>) object;
                LogUtils.v(TAG, "completeUser " + distritoList.toString());
                requestCallBackLogin.onRequestSuccess(distritoList, type);
            }

            @Override
            public void onRequestFailure(Throwable e) {

            }
        });
    }
}
