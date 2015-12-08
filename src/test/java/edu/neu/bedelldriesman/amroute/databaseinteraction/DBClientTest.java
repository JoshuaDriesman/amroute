package edu.neu.bedelldriesman.amroute.databaseinteraction;

import edu.neu.bedelldriesman.amroute.AmrouteApplication;
import edu.neu.bedelldriesman.amroute.entitymodels.City;
import edu.neu.bedelldriesman.amroute.entitymodels.Equipment;
import edu.neu.bedelldriesman.amroute.entitymodels.Route;
import edu.neu.bedelldriesman.amroute.entitymodels.Stop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Joshua Driesman on 12/5/2015.
 *
 * Copyright 2015 Joshua Driesman, All rights reserved.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AmrouteApplication.class)
public class DBClientTest {

    @Autowired
    private JdbcTemplate template;

    @Test
    public void testGetAllRoutes() {
        DBClientImpl dbClient = new DBClientImpl(template);

        ArrayList<Route> r = dbClient.getAllRoutes();
        ArrayList<String> names = new ArrayList<>();

        r.forEach(n -> names.add(n.getName()));

        assertTrue(names.contains("Acela Express"));
        assertTrue(names.contains("Downeaster"));
        assertTrue(names.contains("Capitol Limited"));
    }

    @Test
    public void testGetServingRoutes() {
        DBClientImpl dbClient = new DBClientImpl(template);

        ArrayList<Route> r = dbClient.getServingRoutes(1);

        assertEquals("Capitol Limited", r.get(0).getName());
        assertEquals("Acela Express", r.get(1).getName());
    }

    @Test
    public void testGetCitiesForSchedule() {
        DBClientImpl dbClient = new DBClientImpl(template);

        ArrayList<City> r = dbClient.getCitiesForSchedule(1);
        ArrayList<String> cityNames = new ArrayList<>();

        r.forEach(c -> cityNames.add(c.getName()));

        assertTrue(cityNames.contains("Rockville"));
    }

    @Test
    public void testGetStopsForSchedule() {
        DBClientImpl dbClient = new DBClientImpl(template);

        ArrayList<Stop> r = dbClient.getStopsForSchedule(1);
        ArrayList<Integer> scheduleIds = new ArrayList<>();
        ArrayList<String> cityNames = new ArrayList<>();

        r.forEach(s -> scheduleIds.add(s.getScheduleId()));
        r.forEach(s -> cityNames.add(s.getCity().getName()));

        for (Integer id : scheduleIds) {
            assertEquals(1, id.intValue());
        }

        cityNames.contains("Rockville");
    }

    @Test
    public void testGetEquipmentForRoute() {
        DBClientImpl dbClient = new DBClientImpl(template);

        ArrayList<Equipment> equipment = dbClient.getEquipmentForRoute("Acela Express");
        ArrayList<String> series = new ArrayList<>();

        equipment.forEach(e -> series.add(e.getSeries()));

        series.forEach(s -> assertEquals("Acela High Speed Trainset", s));
    }
}
