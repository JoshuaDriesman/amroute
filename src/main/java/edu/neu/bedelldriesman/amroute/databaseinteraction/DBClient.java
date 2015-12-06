package edu.neu.bedelldriesman.amroute.databaseinteraction;

import edu.neu.bedelldriesman.amroute.entitymodels.Route;

import java.util.ArrayList;

/**
 * Created by Joshua Driesman on 11/16/2015.
 */
public interface DBClient {
    /*boolean create(Object input);
    boolean delete(int id);
    Object get(int id);*/
    ArrayList<Route> getServingRoutes(String cityName);
}
