package com.letsgo.appletsgo.data.entity.response;

import com.letsgo.appletsgo.data.entity.LoginEntity;

/**
 * Created by louislopez on 3/06/17.
 */

public class LoginResponse extends BaseResponse {

    private LoginEntity data;

    public LoginEntity getData() {
        return data;
    }

    public void setData(LoginEntity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "data=" + data +
                '}';
    }
}
