package com.letsgo.appletsgo.domain.model.entity;



import java.io.Serializable;
import java.util.List;

/**
 * Created by louislopez on 6/03/17.
 */

public class DateGroup implements Serializable {
    private String schedul_date;
    private List<TimesGroup> group_time;

    //private List<TimesGroupEntity> times_group;


    public String getSchedule_date() {
        return schedul_date;
    }

    public void setSchedule_date(String schedule_date) {
        this.schedul_date = schedule_date;
    }

    public List<TimesGroup> getSchedule_time() {
        return group_time;
    }

    public void setSchedule_time(List<TimesGroup> schedule_time) {
        this.group_time = schedule_time;
    }

    @Override
    public String toString() {
        return "DateGroup{" +
                "schedul_date='" + schedul_date + '\'' +
                ", schedul_time=" + group_time +
                '}';
    }
}
