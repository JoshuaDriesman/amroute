package edu.neu.bedelldriesman.amroute.entitymodels;

import java.util.Objects;

/**
 * Created by Joshua Driesman on 11/16/2015.
 *
 * Copyright 2015 Joshua Driesman, All rights reserved.
 */
public class City {
    final private int cityId;
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

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (cityId != city.cityId) return false;
        if (!name.equals(city.name)) return false;
        if (!state.equals(city.state)) return false;
        return region.equals(city.region);

    }

    @Override
    public int hashCode() {
        int result = cityId;
        result = 31 * result + name.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + region.hashCode();
        return result;
    }
}
