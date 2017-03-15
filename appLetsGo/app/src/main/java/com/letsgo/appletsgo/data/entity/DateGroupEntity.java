package com.letsgo.appletsgo.data.entity;

import com.letsgo.appletsgo.domain.model.entity.TimesGroup;

import java.io.Serializable;
import java.util.List;

/**  "schedule_date": "2017-03-08",
 "schedule_time": "01:00:00"
 * Created by louislopez on 5/03/17.
 */

public class DateGroupEntity implements Serializable{
    private String schedul_date;
    private List<TimesGroupEntity> group_time;

    //private List<TimesGroupEntity> times_group;


    public String getSchedule_date() {
        return schedul_date;
    }

    public void setSchedule_date(String schedule_date) {
        this.schedul_date = schedule_date;
    }

    public List<TimesGroupEntity> getSchedule_time() {
        return group_time;
    }

    public void setSchedule_time(List<TimesGroupEntity> schedule_time) {
        this.group_time = schedule_time;
    }

    @Override
    public String toString() {
        return "DateGroupEntity{" +
                "schedul_date='" + schedul_date + '\'' +
                ", schedul_time=" + group_time +
                '}';
    }
}
