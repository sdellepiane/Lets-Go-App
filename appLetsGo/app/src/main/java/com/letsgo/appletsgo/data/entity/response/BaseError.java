package com.letsgo.appletsgo.data.entity.response;

import java.io.Serializable;

/**
 * Created by Luis Lopez on 25/04/2016.
 */
public class BaseError implements Serializable {

    private String element;
    private String msg;

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
