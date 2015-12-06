package edu.neu.bedelldriesman.amroute.databaseinteraction;

import edu.neu.bedelldriesman.amroute.entitymodels.*;

import java.util.ArrayList;

/**
 * Created by Joshua Driesman on 11/16/2015.
 *
 * Copyright 2015 Joshua Driesman, All rights reserved.
 */
public interface DBClient {
    // Retrieval methods
    ArrayList<Route> getAllRoutes();

    ArrayList<Route> getServingRoutes(String cityName);

    ArrayList<Schedule> getScheduleForRoute(String route);

    ArrayList<Stop> getStopsForSchedule(int sId);

    ArrayList<City> getCitiesForSchedule(int sId);

    ArrayList<Equipment> getEquipmentForRoute(String route);

    // Delete methods
    int deleteRoute(String route);

    // Update methods
    void changeRouteName(String oldName, String newName);
}
