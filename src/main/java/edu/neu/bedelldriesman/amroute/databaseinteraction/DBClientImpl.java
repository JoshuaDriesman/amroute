package edu.neu.bedelldriesman.amroute.databaseinteraction;

import edu.neu.bedelldriesman.amroute.entitymodels.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Joshua Driesman on 12/5/2015.
 *
 * Copyright 2015 Joshua Driesman, All rights reserved.
 */
@Component
public class DBClientImpl implements DBClient {

    private final JdbcTemplate template;

    @Autowired
    public DBClientImpl(JdbcTemplate template) {
        this.template = template;

    }

    @Override
    public ArrayList<Route> getAllRoutes() {
        ArrayList<Route> result = new ArrayList<>();

        template.query("SELECT name FROM routes",
                (rs, rowNumber) -> new Route(rs.getString("name")))
        .forEach(result::add);

        return result;
    }

    @Override
    public ArrayList<Route> getServingRoutes(String cityName) {
        ArrayList<Route> result = new ArrayList<>();

        SimpleJdbcCall call = new SimpleJdbcCall(template)
                .withProcedureName("getServingRoutes")
                .returningResultSet("rs1", new SingleColumnRowMapper<>());

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("c", cityName);

        Object out = call.execute(in).get("rs1");
        ArrayList rows;

        if (out instanceof ArrayList) {
            rows = (ArrayList) out;
        } else {
            throw new IllegalStateException("Did not get rows out of database.");
        }

        for (Object r : rows) {
            String row = (String) r;
            Route route = new Route(row);

            result.add(route);
        }

        return result;
    }
}
