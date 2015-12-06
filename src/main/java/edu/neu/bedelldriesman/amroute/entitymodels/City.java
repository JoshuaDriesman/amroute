package edu.neu.bedelldriesman.amroute.entitymodels;

import java.util.Objects;

/**
 * Created by Joshua Driesman on 11/16/2015.
 *
 * Copyright 2015 Joshua Driesman, All rights reserved.
 */
public class City {
    private int cityId;
    private String name;
    private String state;
    private String region;

    public City(int cityId, String name, String state, String region) {
        this.cityId = cityId;

        Objects.requireNonNull(name);
        Objects.requireNonNull(state);
        Objects.requireNonNull(region);
        this.name = name;
        this.state = state;
        this.region = region;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Objects.requireNonNull(name);

        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        Objects.requireNonNull(state);

        this.state = state;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        Objects.requireNonNull(region);

        this.region = region;
    }
}
