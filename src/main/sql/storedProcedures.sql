use 'amroute';

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
$$
DELIMITER ;

/* Procedures */

/* Find all routes for a certain city */
DROP PROCEDURE IF EXISTS getServingRoutes;
DELIMITER $$
CREATE PROCEDURE getServingRoutes(IN cId INT)
BEGIN
SELECT DISTINCT route FROM (cities JOIN schedulecities ON cities.idCities = schedulecities.cityId
	AND cities.idCities = cId) 
	JOIN schedule ON schedulecities.scheduleId = schedule.idSchedule
UNION
SELECT DISTINCT route FROM cities JOIN schedule ON 
    (cities.idCities = schedule.origin OR cities.idCities = schedule.termination)
    AND cities.idCities = cId;
END
$$
DELIMITER ;

/* Find all routes serving a certain city between certain times */
DROP PROCEDURE IF EXISTS getServingRoutesByTime;
DELIMITER $$
CREATE PROCEDURE getServingRoutesByTime(IN cId INT, IN lowerTime TIME, IN upperTime TIME)
BEGIN
SELECT DISTINCT route FROM (cities JOIN schedulecities ON cities.idCities = schedulecities.cityId
	AND cities.idCities = cId) 
	JOIN schedule ON schedulecities.scheduleId = schedule.idSchedule
    AND schedulecities.time > lowerTime AND schedulecities.time < upperTime
UNION
SELECT DISTINCT route FROM cities JOIN schedule ON 
    cities.idCities = schedule.termination AND cities.idCities = cId AND schedule.termTime > lowerTime
    AND schedule.termTime < upperTime
UNION 
SELECT DISTINCT route FROM cities JOIN schedule ON
	cities.idCities = schedule.origin AND cities.idCities = cId AND schedule.originTime > lowerTime AND
    schedule.originTime < upperTime;
END
$$
DELIMITER ;

/* Procedure to get all schedules for route */
DROP PROCEDURE IF EXISTS getSchedulesForRoute;
DELIMITER $$
CREATE PROCEDURE getSchedulesForRoute(IN r VARCHAR(45))
BEGIN
SELECT * FROM schedule WHERE route = r ORDER BY originTime;
END
$$
DELIMITER ;

/* Procedure to get all stops for a route */
DROP PROCEDURE IF EXISTS getAllPossibleStops;
DELIMITER $$
CREATE PROCEDURE getAllPossibleStops(IN r VARCHAR(45))
BEGIN
SELECT DISTINCT cities.name FROM (schedule JOIN schedulecities 
		ON schedule.idSchedule = schedulecities.scheduleId AND schedule.route = r)
	JOIN cities on schedulecities.cityId = cities.idCities;
END
$$
DELIMITER ;

/* Procedure to get all origin cities for a route */
DROP PROCEDURE IF EXISTS getAllPossibleOriginCities;
DELIMITER $$
CREATE PROCEDURE getAllPossibleOriginCities(IN r VARCHAR(45))
BEGIN
SELECT DISTINCT cities.name FROM (schedule JOIN cities
	ON schedule.origin = cities.idCities AND schedule.route = r);
END
$$
DELIMITER ;

/* Procedure to get all origin cities for a route */
DROP PROCEDURE IF EXISTS getAllPossibleTermCities;
DELIMITER $$
CREATE PROCEDURE getAllPossibleTermCities(IN r VARCHAR(45))
BEGIN
SELECT DISTINCT cities.name FROM (schedule JOIN cities
	ON schedule.termination = cities.idCities AND schedule.route = r);
END
$$
DELIMITER ;

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
$$
DELIMITER ;

/* Get stops for schedule */
DROP PROCEDURE IF EXISTS getStopsForSchedule;
DELIMITER $$
CREATE PROCEDURE getStopsForSchedule(IN sId INT)
BEGIN
SELECT * FROM schedulecities WHERE scheduleId = sId ORDER BY nextDay, time;
END
$$
DELIMITER ;

/* Get cities for schedule */
DROP PROCEDURE IF EXISTS getCitiesForSchedule;
DELIMITER $$
CREATE PROCEDURE getCitiesForSchedule(IN sId INT)
BEGIN
SELECT idCities, region, name, state FROM schedulecities JOIN cities ON
	schedulecities.cityId = cities.idCities AND scheduleId = sId;
END
$$
DELIMITER ;

/* Gets city for Id */
DROP PROCEDURE IF EXISTS getCity;
DELIMITER $$
CREATE PROCEDURE getCity(IN cId INT)
BEGIN
SELECT * FROM cities WHERE idCities = cId;
END
$$
DELIMITER ;

/* Get equipment for route */
DROP PROCEDURE IF EXISTS getEquipmentForRoute;
DELIMITER $$
CREATE PROCEDURE getEquipmentForRoute(IN r VARCHAR(45))
BEGIN
SELECT idEquipment, configuration, series FROM 
	(routes JOIN routeequipment ON routes.name = routeequipment.routeName AND routes.name = r)
    JOIN equipment ON equipment.idEquipment = routeequipment.equipmentId;
END
$$
DELIMITER ;

/* Get configurations for equipment */
DROP PROCEDURE IF EXISTS getConfigurationsForSeries;
DELIMITER $$
CREATE PROCEDURE getConfigurationsForSeries(IN seriesName VARCHAR(45))
BEGIN
SELECT configuration FROM equipment WHERE series = seriesName;
END
$$
DELIMITER ;

/* Add equipment to route */
DROP PROCEDURE IF EXISTS addEquipmentToRoute;
DELIMITER $$
CREATE PROCEDURE addEquipmentToRoute(IN equipmentSeries VARCHAR(45), IN equipmentConfig VARCHAR(45), IN route VARCHAR(45))
BEGIN
DECLARE equipId INT;
DECLARE csr CURSOR FOR
SELECT idEquipment FROM equipment WHERE series = equipmentSeries AND configuration = equipmentConfig;

OPEN csr;
FETCH csr INTO equipId;
CLOSE csr;

INSERT INTO routeequipment (routeName, equipmentId) VALUES (route, equipId);

END
$$
DELIMITER ;