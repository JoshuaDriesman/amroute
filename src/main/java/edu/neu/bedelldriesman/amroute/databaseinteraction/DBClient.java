package edu.neu.bedelldriesman.amroute.databaseinteraction;

import edu.neu.bedelldriesman.amroute.entitymodels.City;
import edu.neu.bedelldriesman.amroute.entitymodels.Route;
import edu.neu.bedelldriesman.amroute.entitymodels.Schedule;
import edu.neu.bedelldriesman.amroute.entitymodels.Stop;

import java.util.ArrayList;

/**
 * Created by Joshua Driesman on 11/16/2015.
 *
 * Copyright 2015 Joshua Driesman, All rights reserved.
 */
public interface DBClient {
    /*boolean create(Object input);
    boolean delete(int id);
    Object get(int id);*/
    ArrayList<Route> getAllRoutes();

    ArrayList<Route> getServingRoutes(String cityName);

    ArrayList<Schedule> getScheduleForRoute(String route);

    ArrayList<Stop> getStopsForSchedule(int sId);

    ArrayList<City> getCitiesForSchedule(int sId);
}
