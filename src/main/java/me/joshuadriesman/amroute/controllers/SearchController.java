package me.joshuadriesman.amroute.controllers;

import me.joshuadriesman.amroute.databaseinteraction.DBClientImpl;
import me.joshuadriesman.amroute.entitymodels.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * Created by Joshua Driesman on 12/5/2015.
 * <p>
 * Copyright 2015 Joshua Driesman, All rights reserved.
 */
@Controller
public class SearchController {
    @Autowired
    JdbcTemplate temp;

    DBClientImpl client;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchForm(@RequestParam("city") int cityId,
                             @RequestParam(value = "lowerTime", required = false) String lowerTimeString,
                             @RequestParam(value = "upperTime", required = false) String upperTimeString,
                             Model model) {
        client = new DBClientImpl(temp);

        ArrayList<Route> routes;

        if (!lowerTimeString.equals("") && !upperTimeString.equals("")) {
            routes = client.getServingRoutes(cityId, lowerTimeString, upperTimeString);
        } else {
            routes = client.getServingRoutes(cityId);
        }

        ArrayList<String> names = new ArrayList<>();
        routes.forEach(r -> names.add(r.getName()));

        model.addAttribute("names", names);
        model.addAttribute("cities", client.getAllCities());

        return "index";
    }
}
