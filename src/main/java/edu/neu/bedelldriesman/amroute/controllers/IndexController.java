package edu.neu.bedelldriesman.amroute.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Joshua Driesman on 12/6/2015.
 * <p>
 * Copyright 2015 Joshua Driesman, All rights reserved.
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String getIndex() {
        return "index";
    }
}
