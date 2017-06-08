package com.letsgo.appletsgo.domain.model.entity;

import java.io.Serializable;

/**
 * Created by louislopez on 4/06/17.
 */

public class CompleteUser implements Serializable {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CompleteUserEntity{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
