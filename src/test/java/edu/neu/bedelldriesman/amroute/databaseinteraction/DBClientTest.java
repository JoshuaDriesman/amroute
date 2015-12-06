package edu.neu.bedelldriesman.amroute.databaseinteraction;

import edu.neu.bedelldriesman.amroute.AmrouteApplication;
import edu.neu.bedelldriesman.amroute.entitymodels.Route;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Joshua Driesman on 12/5/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AmrouteApplication.class)
public class DBClientTest {

    @Autowired
    private JdbcTemplate template;

    @Test
    public void testGetServingRoutes() {
        DBClientImpl dbClient = new DBClientImpl(template);

        ArrayList<Route> r = dbClient.getServingRoutes("Washington");

        assertEquals("Capitol Limited", r.get(0).getName());
        assertEquals("Acela Express", r.get(1).getName());
    }
}
