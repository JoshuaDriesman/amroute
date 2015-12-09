package edu.neu.bedelldriesman.amroute.controllers;

import edu.neu.bedelldriesman.amroute.databaseinteraction.DBClient;
import edu.neu.bedelldriesman.amroute.databaseinteraction.DBClientImpl;
import edu.neu.bedelldriesman.amroute.entitymodels.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by Joshua Driesman on 12/6/2015.
 * <p>
 * Copyright 2015 Joshua Driesman, All rights reserved.
 */
@Controller
public class EquipmentController {
    @Autowired
    JdbcTemplate temp;

    @RequestMapping(path = "/route/equipment", method = RequestMethod.GET)
    public String getEquipment(@RequestParam(value = "routeId", required = true) String routeId, Model model) {
        DBClientImpl client = new DBClientImpl(temp);

        ArrayList<Equipment> equipment = client.getEquipmentForRoute(routeId);

        model.addAttribute("equipment", equipment.toArray());
        model.addAttribute("route", routeId);

        return "equipment";
    }

    @RequestMapping(path = "/route/equipment/configurations", method = RequestMethod.GET)
    public
    @ResponseBody
    ArrayList<String> getEquipmentConfigurations(@RequestParam(value = "seriesId") String seriesId) {
        DBClient client = new DBClientImpl(temp);

        ArrayList<String> configurations = client.getConfigurationsForSeries(seriesId);

        return configurations;
    }

}
