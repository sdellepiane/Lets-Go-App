package com.letsgo.appletsgo.data.entity;

import java.io.Serializable;

/**
 * Created by louislopez on 4/06/17.
 "msg": "Editado correctamente"
 */

public class CompleteUserEntity implements Serializable {
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
