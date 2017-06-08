package com.letsgo.appletsgo.presenter;

import com.google.android.gms.plus.People;
import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.data.TypeService;
import com.letsgo.appletsgo.data.entity.raw.CompleteUserRaw;
import com.letsgo.appletsgo.data.entity.raw.LoginRaw;
import com.letsgo.appletsgo.data.mapper.LoginDataMapper;
import com.letsgo.appletsgo.domain.interactor.LoginInteractor;
import com.letsgo.appletsgo.domain.model.entity.CompleteUser;
import com.letsgo.appletsgo.domain.model.entity.Login;
import com.letsgo.appletsgo.domain.repository.LoginServiceRepository;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackLogin;
import com.letsgo.appletsgo.repository.Login.LoginDataRepository;
import com.letsgo.appletsgo.repository.datasource.Login.LoginDataStoreFactory;
import com.letsgo.appletsgo.view.LoginView;

/**
 * Created by louislopez on 4/06/17.
 */

public class LoginPresenter implements Presenter<LoginView>, RequestCallBackLogin {
    private static final String TAG = "LoginPresenter";
    private LoginView loginView;
    private LoginInteractor loginInteractor;
    private LoginServiceRepository serviceRepository;
    private LoginRaw raw;

    public void setLogin(LoginRaw raw){
        if (raw != null){
            loginView.showLoading();
            loginInteractor.setLogin(raw, this);
        }
    }

    public void setCompleteUserRegister(CompleteUserRaw raw){
        if (raw != null){
            loginView.showLoading();
            loginInteractor.setCompleteUserRegister(raw, this);
        }
    }

    @Override
    public void attachedView(LoginView view) {
        loginView = view;
        serviceRepository = new LoginDataRepository(new LoginDataStoreFactory(loginView.getContext()), new LoginDataMapper());
        loginInteractor = new LoginInteractor(serviceRepository);
    }

    @Override
    public void deatchView() {
        loginView = null;
    }

    @Override
    public void onRequestSuccess(Object object) {




    }

    @Override
    public void onRequestSuccess(Object object, int type) {
        LogUtils.v(TAG, "onRequestSuccess " + object);
        switch (type) {
            case TypeService.LOGIN_FACEBOOK:
                Login login = (Login) object;
                if (login != null){
                    loginView.loginFacebook(login);
                }
                break;
            case TypeService.COMPLETE_LOGIN:
                CompleteUser completeUser = (CompleteUser) object;
                if (completeUser != null){
                    loginView.completeUserRegister(completeUser);
                }
                break;
        }
    }

    @Override
    public void onRequestFailure(Throwable e) {

    }
}
