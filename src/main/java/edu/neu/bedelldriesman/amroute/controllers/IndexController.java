package edu.neu.bedelldriesman.amroute.controllers;

import edu.neu.bedelldriesman.amroute.databaseinteraction.DBClient;
import edu.neu.bedelldriesman.amroute.databaseinteraction.DBClientImpl;
import edu.neu.bedelldriesman.amroute.entitymodels.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Created by Joshua Driesman on 12/6/2015.
 * <p>
 * Copyright 2015 Joshua Driesman, All rights reserved.
 */
@Controller
public class IndexController {

    @Autowired
    JdbcTemplate template;

    @RequestMapping("/")
    public String getIndex(Model model) {
        DBClient client = new DBClientImpl(template);

        ArrayList<Route> routes = client.getAllRoutes();
        ArrayList<String> names = new ArrayList<>();

        routes.forEach(n -> names.add(n.getName()));

        model.addAttribute("names", names.toArray());

        return "index";
    }
}
