package com.letsgo.appletsgo.view;

import com.letsgo.appletsgo.data.entity.response.LoginResponse;
import com.letsgo.appletsgo.domain.model.entity.CompleteUser;
import com.letsgo.appletsgo.domain.model.entity.Login;

/**
 * Created by louislopez on 3/06/17.
 */

public interface LoginView extends BaseView {
    void loginFacebook(Login login);
    void completeUserRegister(CompleteUser completeUser);
    void showLoading();
    void hideLoading();
}
