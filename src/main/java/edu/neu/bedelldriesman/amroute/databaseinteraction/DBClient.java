package edu.neu.bedelldriesman.amroute.databaseinteraction;

import edu.neu.bedelldriesman.amroute.entitymodels.Route;

/**
 * Created by Joshua Driesman on 11/16/2015.
 */
public interface DBClient {
    /*boolean create(Object input);
    boolean delete(int id);
    Object get(int id);*/
    Route getServingRoutes(String cityName);
}
