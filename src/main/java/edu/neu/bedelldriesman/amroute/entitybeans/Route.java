package edu.neu.bedelldriesman.amroute.entitybeans;

import java.util.Objects;

/**
 * Created by Joshua Driesman on 11/16/2015.
 */
public class Route {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Route(String name) {
        Objects.requireNonNull(name);

        this.name = name;
    }
}
