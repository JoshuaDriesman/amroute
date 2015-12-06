package edu.neu.bedelldriesman.amroute.entitymodels;

import java.util.Objects;

/**
 * Created by Joshua Driesman on 11/16/2015.
 *
 * Copyright 2015 Joshua Driesman, All rights reserved.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stop stop = (Stop) o;

        if (scheduleId != stop.scheduleId) return false;
        if (cityId != stop.cityId) return false;
        return city.equals(stop.city);

    }

    @Override
    public int hashCode() {
        int result = scheduleId;
        result = 31 * result + cityId;
        result = 31 * result + city.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "scheduleId=" + scheduleId +
                ", cityId=" + cityId +
                ", city=" + city +
                '}';
    }
}
