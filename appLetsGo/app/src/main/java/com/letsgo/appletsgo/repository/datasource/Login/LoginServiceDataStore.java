package com.letsgo.appletsgo.repository.datasource.Login;

import com.letsgo.appletsgo.data.entity.raw.CompleteUserRaw;
import com.letsgo.appletsgo.data.entity.raw.LoginRaw;
import com.letsgo.appletsgo.repository.Login.RepositoryCallBackLogin;

/**
 * Created by louislopez on 3/06/17.
 */

public interface LoginServiceDataStore {

    void setLogin(LoginRaw raw, RepositoryCallBackLogin repositoryCallBackLogin);
    void setCompleteLogin(CompleteUserRaw raw, RepositoryCallBackLogin repositoryCallBackLogin);
}
