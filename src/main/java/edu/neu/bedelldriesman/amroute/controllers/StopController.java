package edu.neu.bedelldriesman.amroute.controllers;

import edu.neu.bedelldriesman.amroute.databaseinteraction.DBClient;
import edu.neu.bedelldriesman.amroute.databaseinteraction.DBClientImpl;
import edu.neu.bedelldriesman.amroute.entitymodels.City;
import edu.neu.bedelldriesman.amroute.response.BasicInsertResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by Joshua Driesman on 12/6/2015.
 * <p>
 * Copyright 2015 Joshua Driesman, All rights reserved.
 */

@RestController
public class StopController {
    @Autowired
    JdbcTemplate template;

    @RequestMapping(path = "/stop/delete", method = RequestMethod.POST)
    public void deleteStop(@RequestParam(name = "scheduleId") int scheduleId,
                             @RequestParam(name = "cityId") int cityId) {
        DBClient client = new DBClientImpl(template);
        client.deleteStop(scheduleId, cityId);
    }

    @RequestMapping(path = "/stop/add", method = RequestMethod.POST)
    public @ResponseBody
    BasicInsertResponse addStop(@RequestParam(name = "scheduleId") int scheduleId,
                                @RequestParam(name = "cityId") int cityId,
                                @RequestParam(name = "time") String timeString) {

        DBClient client = new DBClientImpl(template);

        ArrayList<City> cities = client.getCitiesForSchedule(scheduleId);
        ArrayList<Integer> cityIds = new ArrayList<>();
        cities.forEach(c -> cityIds.add(c.getCityId()));

        if (cityIds.contains(cityId)) {
            return new BasicInsertResponse(false, "There is already a stop in that city.");
        }

        client.insertStop(scheduleId, cityId, Time.valueOf(timeString));

        return new BasicInsertResponse(true, null);
    }
}
