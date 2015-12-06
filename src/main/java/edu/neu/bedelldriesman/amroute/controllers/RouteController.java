package edu.neu.bedelldriesman.amroute.controllers;

import edu.neu.bedelldriesman.amroute.databaseinteraction.DBClientImpl;
import edu.neu.bedelldriesman.amroute.entitymodels.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * Created by Joshua Driesman on 12/6/2015.
 * <p>
 * Copyright 2015 Joshua Driesman, All rights reserved.
 */

@Controller
public class RouteController {
    @Autowired
    JdbcTemplate temp;

    @RequestMapping(path = "/route", method = RequestMethod.GET)
    public String getRoute(@RequestParam(value = "id", required = true) String id, Model model) {
        DBClientImpl client = new DBClientImpl(temp);
        ArrayList<Schedule> schedules = client.getScheduleForRoute(id);

        model.addAttribute("schedules", schedules);
        model.addAttribute("id", id);

        return "route";
    }
}
