package edu.neu.bedelldriesman.amroute.entitybeans;

import java.util.Objects;

/**
 * Created by Joshua Driesman on 11/16/2015.
 */
public class Stop {
    private int scheduleId;
    private int cityId;

    private City city;

    public Stop(City city, int scheduleId, int cityId) {
        Objects.requireNonNull(city);

        this.city = city;
        this.scheduleId = scheduleId;
        this.cityId = cityId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        Objects.requireNonNull(city);

        this.city = city;
    }
}
