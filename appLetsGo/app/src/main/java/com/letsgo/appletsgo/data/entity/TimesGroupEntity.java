package com.letsgo.appletsgo.data.entity;

import java.io.Serializable;

/**
 * Created by louislopez on 5/03/17.
 */

public class TimesGroupEntity implements Serializable {
    private String schedul_time;

    public String getSchedul_time() {
        return schedul_time;
    }

    public void setSchedul_time(String schedul_time) {
        this.schedul_time = schedul_time;
    }

    @Override
    public String toString() {
        return "TimesGroupEntity{" +
                "schedul_time='" + schedul_time + '\'' +
                '}';
    }
}
