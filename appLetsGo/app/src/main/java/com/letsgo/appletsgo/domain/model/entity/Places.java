package com.letsgo.appletsgo.domain.model.entity;

import com.letsgo.appletsgo.data.entity.DateGroupEntity;
import com.letsgo.appletsgo.data.entity.PricesEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by louislopez on 6/03/17.
 "latitude": "-12.082811",
 "longitude": "-77.0509371",
 */

public class Places implements Serializable {
    private String id_activities_places;
    private String name_place;
    private String addresses;
    private String latitude;
    private String longitude;
    private List<DateGroup> group_date;
    private List<Prices> prices;

    public String getId_activities_places() {
        return id_activities_places;
    }

    public void setId_activities_places(String id_activities_places) {
        this.id_activities_places = id_activities_places;
    }

    public String getName_place() {
        return name_place;
    }

    public void setName_place(String name_place) {
        this.name_place = name_place;
    }

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<DateGroup> getGroup_date() {
        return group_date;
    }

    public void setGroup_date(List<DateGroup> group_date) {
        this.group_date = group_date;
    }

    public List<Prices> getPrices() {
        return prices;
    }

    public void setPrices(List<Prices> prices) {
        this.prices = prices;
    }

    @Override
    public String toString() {
        return "Places{" +
                "id_activities_places='" + id_activities_places + '\'' +
                ", name_place='" + name_place + '\'' +
                ", addresses='" + addresses + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", group_date=" + group_date +
                ", prices=" + prices +
                '}';
    }
}
