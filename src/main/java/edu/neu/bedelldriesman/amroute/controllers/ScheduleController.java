package edu.neu.bedelldriesman.amroute.controllers;

import edu.neu.bedelldriesman.amroute.databaseinteraction.DBClient;
import edu.neu.bedelldriesman.amroute.databaseinteraction.DBClientImpl;
import edu.neu.bedelldriesman.amroute.entitymodels.City;
import edu.neu.bedelldriesman.amroute.entitymodels.Schedule;
import edu.neu.bedelldriesman.amroute.entitymodels.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Joshua Driesman on 12/6/2015.
 * <p>
 * Copyright 2015 Joshua Driesman, All rights reserved.
 */
@Controller
public class ScheduleController {
    @Autowired
    JdbcTemplate temp;

    @RequestMapping(path = "/schedule/edit", method = RequestMethod.GET)
    public String getSchedule(@RequestParam(value = "scheduleId", required = true) int scheduleId,
                              Model model) {
        DBClientImpl client = new DBClientImpl(temp);

        Schedule s = client.getSchedule(scheduleId);

        List<Stop> stops = s.getStops();

        List<City> cities = client.getAllCities();

        model.addAttribute("schedule", s);
        model.addAttribute("stops", s.getStops());

        cities.remove(s.getOriginCity());
        cities.remove(s.getTermCity());
        model.addAttribute("allOtherCities", cities);

        return "editschedule";
    }

    @RequestMapping(path = "/schedule/edit", method = RequestMethod.POST)
    public @ResponseBody String updateSchedule(@RequestParam(value = "scheduleId") int scheduleId,
                   @RequestParam(value = "origin") int originCity,
                   @RequestParam(value = "term") int termCity) {
        DBClient client = new DBClientImpl(temp);

        client.updateScheduleEndpoints(scheduleId, originCity, termCity);

        return "";
    }
}
