package com.letsgo.appletsgo.domain.repository;

import com.letsgo.appletsgo.data.entity.raw.CompleteUserRaw;
import com.letsgo.appletsgo.data.entity.raw.LoginRaw;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackLogin;

/**
 * Created by louislopez on 3/06/17.
 */

public interface LoginServiceRepository {
    void setLoginReuqest(LoginRaw raw, RequestCallBackLogin requestCallBackLogin);
    void setCompleteUserReuqest(CompleteUserRaw raw, RequestCallBackLogin requestCallBackLogin);

}
