package me.joshuadriesman.amroute.databaseinteraction;

import me.joshuadriesman.amroute.entitymodels.*;

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

    int insertSchedule(int origin, int term, Time originTime, Time termTime, String route);

    void addEquipmentToRoute(String series, String configuration, String route);

    void addRoute(String routeId);

    // Retrieval methods
    ArrayList<Route> getAllRoutes();

    ArrayList<Route> getServingRoutes(int cityId);

    ArrayList<Schedule> getScheduleForRoute(String route);

    ArrayList<Stop> getStopsForSchedule(int sId);

    ArrayList<City> getCitiesForSchedule(int sId);

    ArrayList<Route> getServingRoutes(int cityId, String lowerTimeString, String upperTimeString);

    ArrayList<Equipment> getEquipmentForRoute(String route);

    Schedule getSchedule(int scheduleId);

    ArrayList<City> getAllCities();

    ArrayList<String> getAllEquipmentSeries();

    ArrayList<String> getConfigurationsForSeries(String seriesId);

    // Delete methods
    int deleteRoute(String route);

    int deleteStop(int scheduleId, int cityId);

    int deleteSchedule(int scheduleId);

    void deleteEquipment(int equipId);

    // Update methods
    void changeRouteName(String oldName, String newName);

    void updateScheduleEndpoints(int scheduleId, int origin, int term);
}
