package me.joshuadriesman.amroute.entitymodels;

import java.util.Objects;

/**
 * Created by Joshua Driesman on 11/16/2015.
 *
 * Copyright 2015 Joshua Driesman, All rights reserved.
 */
public class Route {
    private String name;

    public Route(String name) {
        Objects.requireNonNull(name);

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        return name.equals(route.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Route{" +
                "name='" + name + '\'' +
                '}';
    }
}
