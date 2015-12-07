package edu.neu.bedelldriesman.amroute.controllers;

import edu.neu.bedelldriesman.amroute.databaseinteraction.DBClient;
import edu.neu.bedelldriesman.amroute.databaseinteraction.DBClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
