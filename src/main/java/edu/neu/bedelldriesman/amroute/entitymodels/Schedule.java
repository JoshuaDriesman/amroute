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
    private String route;
    private int originCity;
    private int termCity;
    private Time originDepartureTime;
    private Time termArrivalTime;

    private List<Stop> stops;

    public Schedule(String route, int originCity, int termCity,
                    Time originDepartureTime, Time termArrivalTime, List<Stop> stops) {
        Objects.requireNonNull(stops);

        this.stops = new ArrayList<Stop>(stops);
        this.route = route;
        this.originCity = originCity;
        this.termCity = termCity;
        this.originDepartureTime = originDepartureTime;
        this.termArrivalTime = termArrivalTime;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getOriginCity() {
        return originCity;
    }

    public void setOriginCity(int originCity) {
        this.originCity = originCity;
    }

    public int getTermCity() {
        return termCity;
    }

    public void setTermCity(int termCity) {
        this.termCity = termCity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (!route.equals(schedule.route)) return false;
        if (originCity != schedule.originCity) return false;
        if (termCity != schedule.termCity) return false;
        if (!originDepartureTime.equals(schedule.originDepartureTime)) return false;
        if (!termArrivalTime.equals(schedule.termArrivalTime)) return false;
        return stops.equals(schedule.stops);

    }

    @Override
    public int hashCode() {
        int result = route.hashCode();
        result = 31 * result + originCity;
        result = 31 * result + termCity;
        result = 31 * result + originDepartureTime.hashCode();
        result = 31 * result + termArrivalTime.hashCode();
        result = 31 * result + stops.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "routeId=" + route +
                ", originCity=" + originCity +
                ", termCity=" + termCity +
                ", originDepartureTime=" + originDepartureTime +
                ", termArrivalTime=" + termArrivalTime +
                ", stops=" + stops +
                '}';
    }
}
