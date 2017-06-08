package com.letsgo.appletsgo.data.entity;

import com.letsgo.appletsgo.data.entity.response.BaseResponse;

/**
 * Created by louislopez on 4/06/17.
 */

public class CompleteUserResponse extends BaseResponse {
    private CompleteUserEntity data;

    public CompleteUserEntity getData() {
        return data;
    }

    public void setData(CompleteUserEntity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CompleteUserResponse{" +
                "data=" + data +
                '}';
    }
}
