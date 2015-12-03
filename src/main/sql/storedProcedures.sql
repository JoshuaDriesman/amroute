use amroute;

/* Find all routes for a certain city */
DROP PROCEDURE IF EXISTS getServingRoutes;
DELIMITER $$
CREATE PROCEDURE getServingRoutes(IN city VARCHAR(45))
BEGIN
SELECT DISTINCT route FROM (cities JOIN schedulecities ON cities.idCities = schedulecities.cityId) 
	JOIN schedule ON schedulecities.scheduleId = schedule.idSchedule;
END
$$ DELIMITER ;

