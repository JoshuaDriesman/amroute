package edu.neu.bedelldriesman.amroute.databaseinteraction;

import edu.neu.bedelldriesman.amroute.entitymodels.*;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by Joshua Driesman on 11/16/2015.
 *
 * Copyright 2015 Joshua Driesman, All rights reserved.
 */
public interface DBClient {
    // Insert methods
    void insertStop(int scheduleId, int cityId, Time time);

    // Retrieval methods
    ArrayList<Route> getAllRoutes();

    ArrayList<Route> getServingRoutes(String cityName);

    ArrayList<Schedule> getScheduleForRoute(String route);

    ArrayList<Stop> getStopsForSchedule(int sId);

    ArrayList<City> getCitiesForSchedule(int sId);

    ArrayList<Equipment> getEquipmentForRoute(String route);

    Schedule getSchedule(int scheduleId);

    ArrayList<City> getAllCities();

    // Delete methods
    int deleteRoute(String route);

    int deleteStop(int scheduleId, int cityId);

    // Update methods
    void changeRouteName(String oldName, String newName);
}
