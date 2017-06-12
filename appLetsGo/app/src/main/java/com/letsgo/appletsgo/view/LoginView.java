package com.letsgo.appletsgo.view;

import com.letsgo.appletsgo.data.entity.response.LoginResponse;
import com.letsgo.appletsgo.domain.model.entity.CompleteUser;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.domain.model.entity.Login;

import java.util.List;

/**
 * Created by louislopez on 3/06/17.
 */

public interface LoginView extends BaseView {
    void loginFacebook(Login login);
    void completeUserRegister(CompleteUser completeUser);
    void distritosLima(List<Distrito> distritoList);
    void showLoading();
    void hideLoading();
}
