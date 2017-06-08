package com.letsgo.appletsgo.data.mapper;

import com.letsgo.appletsgo.data.entity.CompleteUserResponse;
import com.letsgo.appletsgo.data.entity.response.LoginResponse;
import com.letsgo.appletsgo.domain.model.entity.CompleteUser;
import com.letsgo.appletsgo.domain.model.entity.Login;

/**
 * Created by louislopez on 4/06/17.
 */

public class LoginDataMapper {


    public Login transFormLoginWalkingService(LoginResponse response){
        Login login = null;
        if (response != null){
            try {
                login = new Login();
                login.setId_users(response.getData().getId_users());
                login.setId_facebook(response.getData().getId_facebook());
                login.setEmail(response.getData().getEmail());
                login.setFirst_name(response.getData().getFirst_name());
                login.setLast_name(response.getData().getLast_name());

            }catch (Exception e){

            }
        }
        return login;
    }

    public CompleteUser transFormCompleteUserWalkingService(CompleteUserResponse response){
        CompleteUser completeUser = null;
        if (response != null){
            try {
                completeUser = new CompleteUser();
                completeUser.setMsg(response.getData().getMsg());
            }catch (Exception e){

            }
        }
        return completeUser;
    }
}
