package edu.neu.bedelldriesman.amroute.databaseinteraction;

import edu.neu.bedelldriesman.amroute.entitymodels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua Driesman on 12/5/2015.
 * <p>
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
    public void insertStop(int scheduleId, int cityId, Time time) {
        template.update("INSERT INTO schedulecities (scheduleId, cityId, time) VALUES (?,?,?)",
                new Object[]{scheduleId, cityId, time});
    }

    @Override
    public int insertSchedule(int origin, int term, Time originTime, Time termTime, String route) {
        template.update("INSERT INTO schedule (origin, termination, originTime, termTime, route) VALUES (?, ?, ?, ?, ?)",
                new Object[]{origin, term, originTime, termTime, route});

        List results = template.query("SELECT LAST_INSERT_ID()",
                (rs, row) -> rs.getInt(1));

        int id = (int) results.get(0);

        return id;
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
    public ArrayList<Route> getServingRoutes(int cityId) {
        ArrayList<Route> result = new ArrayList<>();

        SimpleJdbcCall call = new SimpleJdbcCall(template)
                .withProcedureName("getServingRoutes")
                .returningResultSet("rs1", new SingleColumnRowMapper<>());

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("cId", cityId);

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

        SimpleJdbcCall call = new SimpleJdbcCall(template)
                .withProcedureName("getSchedulesForRoute")
                .returningResultSet("rs1", (resultSet, i) ->
                        new Schedule(resultSet.getString(6), resultSet.getInt(2), resultSet.getInt(3),
                                resultSet.getTime(4), resultSet.getTime(5), getStopsForSchedule(resultSet.getInt(1)),
                                resultSet.getInt(1), getCity(resultSet.getInt(2)), getCity(resultSet.getInt(3))));

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("r", route);

        Object resultObject = call.execute(in).get("rs1");
        ArrayList resultArrayList;

        if (resultObject instanceof ArrayList) {
            resultArrayList = (ArrayList) resultObject;
        } else {
            throw new IllegalStateException("Got unexpected stuff from database");
        }

        for (Object row : resultArrayList) {
            result.add((Schedule) row);
        }

        return result;
    }

    @Override
    public ArrayList<Stop> getStopsForSchedule(int sId) {
        ArrayList<Stop> stops = new ArrayList<>();

        SimpleJdbcCall call = new SimpleJdbcCall(template)
                .withProcedureName("getStopsForSchedule")
                .returningResultSet("rs1", (resultSet, i) ->
                        new Stop(getCity(resultSet.getInt(2)), resultSet.getInt(1), resultSet.getInt(2),
                                resultSet.getTime(3), resultSet.getBoolean(4)));

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

        template.query("SELECT * FROM cities WHERE idCities = ?", new Object[]{anInt},
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

    @Override
    public ArrayList<Route> getServingRoutes(int cityId, String lowerTimeString, String upperTimeString) {
        ArrayList<Route> routes = new ArrayList<>();

        SimpleJdbcCall call = new SimpleJdbcCall(template)
                .withProcedureName("getServingRoutesByTime")
                .returningResultSet("rs1", (rs, i) ->
                        new Route(rs.getString(1)));

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("cId", cityId).addValue("lowerTime", Time.valueOf(lowerTimeString))
                .addValue("upperTime", Time.valueOf(upperTimeString));

        Object resultObject = call.execute(in).get("rs1");
        ArrayList resultArrayList;

        if (resultObject instanceof ArrayList) {
            resultArrayList = (ArrayList) resultObject;
        } else {
            throw new IllegalStateException("Got unexpected stuff from database");
        }

        for (Object row : resultArrayList) {
            routes.add((Route) row);
        }

        return routes;
    }

    @Override
    public ArrayList<Equipment> getEquipmentForRoute(String route) {
        ArrayList<Equipment> equipment = new ArrayList<>();

        SimpleJdbcCall call = new SimpleJdbcCall(template)
                .withProcedureName("getEquipmentForRoute")
                .returningResultSet("rs1", (rs, i) ->
                        new Equipment(rs.getInt(1), rs.getString(3), rs.getString(2)));

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("r", route);

        Object resultObject = call.execute(in).get("rs1");
        ArrayList resultArrayList;

        if (resultObject instanceof ArrayList) {
            resultArrayList = (ArrayList) resultObject;
        } else {
            throw new IllegalStateException("Got unexpected stuff from database");
        }

        for (Object row : resultArrayList) {
            equipment.add((Equipment) row);
        }

        return equipment;
    }

    @Override
    public Schedule getSchedule(int scheduleId) {
        ArrayList<Schedule> results = new ArrayList<>();

        template.query("SELECT * FROM schedule WHERE idSchedule = ?", new Object[]{scheduleId},
                (rs, rowNumber) -> new Schedule(rs.getString(6), rs.getInt(2), rs.getInt(3),
                        rs.getTime(4), rs.getTime(5), getStopsForSchedule(rs.getInt(1)),
                        rs.getInt(1), getCity(rs.getInt(2)), getCity(rs.getInt(3))))
                .forEach(results::add);

        return results.get(0);
    }

    @Override
    public ArrayList<City> getAllCities() {
        ArrayList<City> result = new ArrayList<>();

        template.query("SELECT * FROM cities ORDER BY name",
                (rs, i) ->
                        new City(rs.getInt(1), rs.getString(3), rs.getString(4),
                                rs.getString(2)))
                .forEach(result::add);

        return result;
    }

    @Override
    public ArrayList<String> getAllEquipmentSeries() {
        ArrayList<String> equipment = new ArrayList<>();

        template.query("SELECT DISTINCT series FROM equipment",
                (rs, row) -> rs.getString(1))
                .forEach(equipment::add);

        return equipment;
    }

    @Override
    public ArrayList<String> getConfigurationsForSeries(String seriesId) {
        ArrayList<String> configurations = new ArrayList<>();

        SimpleJdbcCall call = new SimpleJdbcCall(template)
                .withProcedureName("getConfigurationsForSeries")
                .returningResultSet("rs1", (rs, row) -> rs.getString(1));

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("seriesName", seriesId);

        Object resultObject = call.execute(in).get("rs1");
        ArrayList resultArrayList;

        if (resultObject instanceof ArrayList) {
            resultArrayList = (ArrayList) resultObject;
        } else {
            throw new IllegalStateException("Got unexpected stuff from database");
        }

        for (Object row : resultArrayList) {
            configurations.add((String) row);
        }

        return configurations;
    }

    @Override
    public int deleteRoute(String route) {
        return template.update("DELETE FROM routes WHERE name = ?", new Object[]{route});
    }

    @Override
    public int deleteStop(int scheduleId, int cityId) {
        return template.update("DELETE FROM schedulecities WHERE scheduleId = ? AND cityId = ?",
                new Object[]{scheduleId, cityId});
    }

    @Override
    public int deleteSchedule(int scheduleId) {
        return template.update("DELETE FROM schedule WHERE idSchedule = ?",
                new Object[]{scheduleId});
    }

    @Override
    public void changeRouteName(String oldName, String newName) {
        template.update("UPDATE routes SET name = ? WHERE name = ?", new Object[]{newName, oldName});
    }

    @Override
    public void updateScheduleEndpoints(int scheduleId, int origin, int term) {
        template.update("UPDATE schedule SET origin = ?, termination = ? WHERE idSchedule = ?",
                new Object[]{origin, term, scheduleId});
    }


}
