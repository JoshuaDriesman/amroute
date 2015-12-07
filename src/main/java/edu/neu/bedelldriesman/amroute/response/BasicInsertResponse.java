package edu.neu.bedelldriesman.amroute.response;

/**
 * Created by Joshua Driesman on 12/6/2015.
 * <p>
 * Copyright 2015 Joshua Driesman, All rights reserved.
 */
public class BasicInsertResponse {
    private boolean good;
    private String msg;

    public BasicInsertResponse(boolean good, String msg) {
        this.good = good;
        this.msg = msg;
    }

    public boolean isGood() {
        return good;
    }

    public String getMsg() {
        return msg;
    }
}
