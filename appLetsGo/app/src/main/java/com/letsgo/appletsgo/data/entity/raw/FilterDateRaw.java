package com.letsgo.appletsgo.data.entity.raw;

import java.io.Serializable;

/**
 * Created by louislopez on 4/03/17.
 */

public class FilterDateRaw implements Serializable {
    private String date_since;
    private String date_until;

    public String getDate_since() {
        return date_since;
    }

    public void setDate_since(String date_since) {
        this.date_since = date_since;
    }

    public String getDate_until() {
        return date_until;
    }

    public void setDate_until(String date_until) {
        this.date_until = date_until;
    }
}
