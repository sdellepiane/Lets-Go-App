package com.letsgo.appletsgo.domain.model.entity;

import java.io.Serializable;

/**
 * Created by louislopez on 9/03/17.
 */

public class TimesGroup implements Serializable {
    private String schedul_time;

    public String getSchedul_time() {
        return schedul_time;
    }

    public void setSchedul_time(String schedul_time) {
        this.schedul_time = schedul_time;
    }

    @Override
    public String toString() {
        return "TimesGroup{" +
                "schedul_time='" + schedul_time + '\'' +
                '}';
    }


}
