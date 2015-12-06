package edu.neu.bedelldriesman.amroute.entitymodels;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Joshua Driesman on 11/16/2015.
 *
 * Copyright 2015 Joshua Driesman, All rights reserved.
 */
public class Schedule {
    final private int scheduleId;
    private City originCity;
    private City termCity;
    private String route;
    private int originCityId;
    private int termCityId;
    private Time originDepartureTime;
    private Time termArrivalTime;

    private List<Stop> stops;

    public Schedule(String route, int originCity, int termCity,
                    Time originDepartureTime, Time termArrivalTime, List<Stop> stops, int scheduleId,
                    City originCity1, City termCity1) {
        this.scheduleId = scheduleId;
        this.originCity = originCity1;
        this.termCity = termCity1;
        Objects.requireNonNull(stops);

        this.stops = new ArrayList<Stop>(stops);
        this.route = route;
        this.originCityId = originCity;
        this.termCityId = termCity;
        this.originDepartureTime = originDepartureTime;
        this.termArrivalTime = termArrivalTime;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getOriginCityId() {
        return originCityId;
    }

    public void setOriginCityId(int originCity) {
        this.originCityId = originCity;
    }

    public int getTermCityId() {
        return termCityId;
    }

    public void setTermCityId(int termCityId) {
        this.termCityId = termCityId;
    }

    public Time getOriginDepartureTime() {
        return originDepartureTime;
    }

    public void setOriginDepartureTime(Time originDepartureTime) {
        this.originDepartureTime = originDepartureTime;
    }

    public Time getTermArrivalTime() {
        return termArrivalTime;
    }

    public void setTermArrivalTime(Time termArrivalTime) {
        this.termArrivalTime = termArrivalTime;
    }

    public List<Stop> getStops() {
        return new ArrayList<Stop>(stops);
    }

    public void setStops(List<Stop> stops) {
        Objects.requireNonNull(stops);

        this.stops = new ArrayList<Stop>(stops);
    }

    public void setOriginCity(City originCity) {
        this.originCity = originCity;
    }

    public City getTermCity() {
        return termCity;
    }

    public void setTermCity(City termCity) {
        this.termCity = termCity;
    }

    public City getOriginCity() {
        return originCity;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", originCity=" + originCity +
                ", termCity=" + termCity +
                ", route='" + route + '\'' +
                ", originCityId=" + originCityId +
                ", termCityId=" + termCityId +
                ", originDepartureTime=" + originDepartureTime +
                ", termArrivalTime=" + termArrivalTime +
                ", stops=" + stops +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (scheduleId != schedule.scheduleId) return false;
        if (originCityId != schedule.originCityId) return false;
        if (termCityId != schedule.termCityId) return false;
        if (!originCity.equals(schedule.originCity)) return false;
        if (!termCity.equals(schedule.termCity)) return false;
        if (!route.equals(schedule.route)) return false;
        if (!originDepartureTime.equals(schedule.originDepartureTime)) return false;
        if (!termArrivalTime.equals(schedule.termArrivalTime)) return false;
        return stops.equals(schedule.stops);

    }

    @Override
    public int hashCode() {
        int result = scheduleId;
        result = 31 * result + originCity.hashCode();
        result = 31 * result + termCity.hashCode();
        result = 31 * result + route.hashCode();
        result = 31 * result + originCityId;
        result = 31 * result + termCityId;
        result = 31 * result + originDepartureTime.hashCode();
        result = 31 * result + termArrivalTime.hashCode();
        result = 31 * result + stops.hashCode();
        return result;
    }

    public int getScheduleId() {
        return scheduleId;
    }
}
