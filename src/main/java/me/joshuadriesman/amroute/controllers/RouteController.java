package me.joshuadriesman.amroute.controllers;

import me.joshuadriesman.amroute.databaseinteraction.DBClient;
import me.joshuadriesman.amroute.databaseinteraction.DBClientImpl;
import me.joshuadriesman.amroute.entitymodels.Schedule;
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

    @RequestMapping(path = "/route/delete", method = RequestMethod.POST)
    public String deleteRoute(@RequestParam(value = "route", required = true) String route) {
        DBClientImpl client = new DBClientImpl(temp);

        client.deleteRoute(route);

        return "redirect:/";
    }

    @RequestMapping(path = "/route/edit", method = RequestMethod.GET)
    public String editRoute(@RequestParam(value = "route", required = true) String route, Model model) {
        model.addAttribute("name", route);

        return "editroute";
    }

    @RequestMapping(path = "/route/edit", method = RequestMethod.POST)
    public String editRoutePost(@RequestParam(value = "name", required = false) String newName,
                                @RequestParam(value = "route", required = true) String routeToUpdate) {
        DBClientImpl client = new DBClientImpl(temp);

        if (newName.length() > 0) {
            client.changeRouteName(routeToUpdate, newName);
        }

        return "redirect:/";
    }

    @RequestMapping(path = "/route/new", method = RequestMethod.POST)
    public String newRoute(@RequestParam(value = "routeId") String routeId) {
        DBClient client = new DBClientImpl(temp);

        if (!routeId.equals("")) {
            client.addRoute(routeId);
        }

        return "redirect:/";
    }
}
