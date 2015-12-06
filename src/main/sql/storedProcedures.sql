use amroute;

/* Functions */
DROP FUNCTION IF EXISTS cityNameForId;
DELIMITER $$
CREATE FUNCTION cityNameForId(cId INT)
RETURNS VARCHAR(45)
BEGIN
DECLARE cityName VARCHAR(45);
DECLARE noneFound INT DEFAULT FALSE;
DECLARE csr CURSOR FOR 
	SELECT name FROM cities WHERE idCities = cId;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET noneFound = TRUE;

OPEN csr;
FETCH csr INTO cityName;
CLOSE csr;

IF noneFound THEN
	SET cityName = null;
END IF;

RETURN cityName;
END
$$ DELIMITER ;

/* Procedures */

/* Find all routes for a certain city */
DROP PROCEDURE IF EXISTS getServingRoutes;
DELIMITER $$
CREATE PROCEDURE getServingRoutes(IN c VARCHAR(45))
BEGIN
SELECT DISTINCT route FROM (cities JOIN schedulecities ON cities.idCities = schedulecities.cityId
	AND cities.name = c) 
	JOIN schedule ON schedulecities.scheduleId = schedule.idSchedule
UNION
SELECT DISTINCT route FROM cities JOIN schedule ON 
    (cities.idCities = schedule.origin OR cities.idCities = schedule.termination)
    AND cities.name = c;
END
$$ DELIMITER ;

/* Procedure to get all schedules for route */
DROP PROCEDURE IF EXISTS getSchedulesForRoute;
DELIMITER $$
CREATE PROCEDURE getSchedulesForRoute(IN r VARCHAR(45))
BEGIN
SELECT * FROM schedule WHERE route = r;
END
$$ DELIMITER ;

/* Procedure to get all stops for a route */
DROP PROCEDURE IF EXISTS getAllPossibleStops;
DELIMITER $$
CREATE PROCEDURE getAllPossibleStops(IN r VARCHAR(45))
BEGIN
SELECT DISTINCT cities.name FROM (schedule JOIN schedulecities 
		ON schedule.idSchedule = schedulecities.scheduleId AND schedule.route = r)
	JOIN cities on schedulecities.cityId = cities.idCities;
END
$$ DELIMITER ;

/* Procedure to get all origin cities for a route */
DROP PROCEDURE IF EXISTS getAllPossibleOriginCities;
DELIMITER $$
CREATE PROCEDURE getAllPossibleOriginCities(IN r VARCHAR(45))
BEGIN
SELECT DISTINCT cities.name FROM (schedule JOIN cities
	ON schedule.origin = cities.idCities AND schedule.route = r);
END
$$ DELIMITER ;

/* Procedure to get all origin cities for a route */
DROP PROCEDURE IF EXISTS getAllPossibleTermCities;
DELIMITER $$
CREATE PROCEDURE getAllPossibleTermCities(IN r VARCHAR(45))
BEGIN
SELECT DISTINCT cities.name FROM (schedule JOIN cities
	ON schedule.termination = cities.idCities AND schedule.route = r);
END
$$ DELIMITER ;

/* Procedure to get all that depart in time range */
DROP PROCEDURE IF EXISTS getStopsInTimeRange;
DELIMITER $$
CREATE PROCEDURE getStopsInTimeRange(IN lower TIME, upper TIME)
BEGIN
SELECT route, cityNameForId(origin) 'From', cityNameForId(termination) 'To', cities.name 'Stop', time
	FROM (schedule JOIN schedulecities ON schedule.idSchedule = schedulecities.scheduleId AND
			schedulecities.time > lower AND schedulecities.time < upper) 
		JOIN cities ON cities.idCities = schedulecities.cityId;
END
$$ DELIMITER ;
