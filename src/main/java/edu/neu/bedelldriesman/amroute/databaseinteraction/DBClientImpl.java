package edu.neu.bedelldriesman.amroute.databaseinteraction;

import edu.neu.bedelldriesman.amroute.entitymodels.City;
import edu.neu.bedelldriesman.amroute.entitymodels.Route;
import edu.neu.bedelldriesman.amroute.entitymodels.Schedule;
import edu.neu.bedelldriesman.amroute.entitymodels.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public ArrayList<Schedule> getScheduleForRoute(String route) {
        ArrayList<Schedule> result = new ArrayList<>();

//        SimpleJdbcCall call = new SimpleJdbcCall(template)
//                .withProcedureName("getSchedulesForRoute")
//                .returningResultSet("rs1", new RowMapper<Object>() {
//
//                    @Override
//                    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
//                        return new Schedule(resultSet.getInt(0), resultSet.getInt(1), resultSet.getInt(2),
//                                resultSet.getTime(3), resultSet.getTime(4), resultSet.getString(5));
//                    }
//                });

        return null;
    }

    @Override
    public ArrayList<Stop> getStopsForSchedule(int sId) {
        ArrayList<Stop> stops = new ArrayList<>();

        SimpleJdbcCall call = new SimpleJdbcCall(template)
                .withProcedureName("getStopsForSchedule")
                .returningResultSet("rs1", (resultSet, i) ->
                        new Stop(getCity(resultSet.getInt(2)), resultSet.getInt(1), resultSet.getInt(2),
                                resultSet.getTime(3)));

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("sId", sId);

        Object resultObject = call.execute(in).get("rs1");
        ArrayList resultArrayList;

        if (resultObject instanceof ArrayList) {
            resultArrayList = (ArrayList) resultObject;
        } else {
            throw new IllegalStateException("Got unexpected stuff from database");
        }

        for (Object row : resultArrayList) {
            stops.add((Stop) row);
        }

        return stops;
    }

    private City getCity(int anInt) {
        ArrayList<City> resultList = new ArrayList<>();

        template.query("SELECT * FROM cities WHERE idCities = ?", new Object[] {anInt},
                (rs, rowNumber) -> new City(rs.getInt(1), rs.getString(3), rs.getString(4),
                        rs.getString(2)))
                .forEach(resultList::add);

        return resultList.get(0);
    }

    @Override
    public ArrayList<City> getCitiesForSchedule(int sId) {
        ArrayList<City> cities = new ArrayList<>();

        SimpleJdbcCall call = new SimpleJdbcCall(template)
                .withProcedureName("getCitiesForSchedule")
                .returningResultSet("rs1", (resultSet, i) ->
                        new City(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(2)));

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("sId", sId);

        Object resultObject = call.execute(in).get("rs1");
        ArrayList resultArrayList;

        if (resultObject instanceof ArrayList) {
            resultArrayList = (ArrayList) resultObject;
        } else {
            throw new IllegalStateException("Got unexpected stuff from database");
        }

        for (Object row : resultArrayList) {
            cities.add((City) row);
        }

        return cities;
    }
}
