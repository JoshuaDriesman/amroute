package edu.neu.bedelldriesman.amroute.entitybeans;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Joshua Driesman on 11/16/2015.
 */
public class Schedule {
    private int routeId;
    private int originCity;
    private int termCity;
    private Time originDepartureTime;
    private Time termArrivalTime;

    private List<Stop> stops;

    public Schedule(int routeId, int originCity, int termCity,
                    Time originDepartureTime, Time termArrivalTime, List<Stop> stops) {
        Objects.requireNonNull(stops);

        this.stops = new ArrayList<Stop>(stops);
        this.routeId = routeId;
        this.originCity = originCity;
        this.termCity = termCity;
        this.originDepartureTime = originDepartureTime;
        this.termArrivalTime = termArrivalTime;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
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
}
