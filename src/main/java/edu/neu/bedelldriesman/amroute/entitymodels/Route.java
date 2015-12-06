package edu.neu.bedelldriesman.amroute.entitymodels;

import java.util.Objects;

/**
 * Created by Joshua Driesman on 11/16/2015.
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
}
