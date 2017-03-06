USE amroute;

/* Insert data for Capitol Limited route */
INSERT INTO Routes (name) VALUES ('Capitol Limited');

INSERT INTO Equipment (configuration, series) VALUES ('Sleeping Car', 'Superliner I'),
	('Sleeping Car', 'Superliner II'), ('Dining Car', 'Superliner I'), ('Sightseer Lounge', 'Superliner II'),
    ('Coach', 'Superliner I'), ('Baggage', 'Superliner I');
    
INSERT INTO RouteEquipment (routeName, equipmentId) VALUES ('Capitol Limited', 1),
	('Capitol Limited', 2), ('Capitol Limited', 3), ('Capitol Limited', 4), ('Capitol Limited', 5),
    ('Capitol Limited', 6);
    
INSERT INTO Cities (region, name, state) VALUES ('southeast', 'Washington', 'DC'), ('southeast', 'Rockville', 'MD'),
	('southeast', 'Harpers Ferry', 'WV'), ('southeast', 'Martinsburg', 'WV'), ('southeast', 'Cumberland', 'MD'), 
    ('northeast', 'Connellsville', 'PA'), ('northeast', 'Pittsburgh', 'PA'), ('midwest', 'Alliance', 'OH'), ('midwest', 'Cleveland', 'OH'),
    ('midwest', 'Elyria', 'OH'), ('midwest', 'Sandusky', 'OH'), ('midwest', 'Toledo', 'OH'), ('midwest', 'Waterloo', 'IN'), 
    ('midwest', 'Elkhart', 'IN'), ('midwest', 'South Bend', 'IN'), ('midwest', 'Chicago', 'IL');
    
INSERT INTO Schedule (origin, termination, originTime, termTime, route) VALUE 
	(1, 16, TIME '16:05:00', TIME '08:45:00', 'Capitol Limited');
    
INSERT INTO ScheduleCities (scheduleId, cityId, time, nextDay) VALUES (1, 2, TIME '16:29:00', 0),
	(1, 3, TIME '17:16:00', 0), (1, 4, TIME '17:45:00', 0), (1, 5, TIME '19:17:00', 0), (1, 6, TIME '21:47:00', 0),
    (1, 7, TIME '23:48:00', 0), (1, 8, TIME '01:39:00', 1), (1, 9, TIME '02:53:00', 1), (1, 10, TIME '03:29:00', 1),
    (1, 11, TIME '04:02:00', 1), (1, 12, TIME '05:08:00', 1), (1, 13, TIME '06:36:00', 1), (1, 14, TIME '07:29:00', 1),
    (1, 15, TIME '07:51:00', 1);
    
/* Insert data for Downeaster */
INSERT INTO Routes (name) VALUES ('Downeaster');

INSERT INTO Equipment (configuration, series) VALUES ('Coach', 'Amfleet-I'), ('Business', 'Amfleet-I'),
	('Cafe', 'Amfleet-I');
    
INSERT INTO RouteEquipment (routeName, equipmentId) VALUES ('Downeaster', 7), ('Downeaster', 8),
	('Downeaster', 9);
    
INSERT INTO Cities (region, name, state) VALUES ('northeast', 'Boston', 'MA'),
	('northeast', 'Woburn', 'MA'), ('northeast', 'Haverhill', 'MA'), ('northeast', 'Exeter', 'NH'),
    ('northeast', 'Durham-UNH', 'NH'), ('northeast', 'Dover', 'NH'), ('northeast', 'Wells', 'ME'),
    ('northeast', 'Saco', 'ME'), ('northeast', 'Old Orchard Beach', 'ME'), ('northeast', 'Portland', 'ME'),
    ('northeast', 'Freeport', 'ME'), ('northeast', 'Brunswick', 'ME');

INSERT INTO Schedule (origin, termination, originTime, termTime, route) VALUES
	(17, 28, TIME '17:00:00', TIME '20:20:00', 'Downeaster');

INSERT INTO ScheduleCities (scheduleId, cityId, time) VALUES
	(2, 18, TIME '17:18:00'), (2, 19, TIME '17:48:00'), (2, 20, TIME '18:09:00'),
    (2, 21, TIME '18:22:00'), (2, 22, TIME '18:30:00'), (2, 23, TIME '18:48:00'),
    (2, 24, TIME '19:05:00'), (2, 26, TIME '19:25:00'), (2, 27, TIME '20:05:00');
    
/* Insert additional schedule for Downeaster */
INSERT INTO Schedule (origin, termination, originTime, termTime, route) VALUES
	(17, 26, TIME '11:55:00', TIME '14:20:00', 'Downeaster');

INSERT INTO ScheduleCities (scheduleId, cityId, time) VALUES
	(3, 18, TIME '12:13:00'), (3, 19, TIME '12:43:00'), (3, 20, TIME '13:04:00'),
    (3, 21, TIME '13:17:00'), (3, 22, TIME '13:24:00'), (3, 23, TIME '13:41:00'),
    (3, 24, TIME '13:58:00');
    
/* Insert Acela Express Data */
INSERT INTO Routes (name) VALUE ('Acela Express');

INSERT INTO Equipment (configuration, series) VALUES ('Business', 'Acela High Speed Trainset'),
	('First', 'Acela High Speed Trainset'), ('Cafe', 'Acela High Speed Trainset');

INSERT INTO RouteEquipment (routeName, equipmentId) VALUES
	('Acela Express', 10), ('Acela Express', 11), ('Acela Express', 12);

INSERT INTO Cities (region, name, state) VALUES
	('southeast', 'BWI', 'MD'), ('southeast', 'Baltimore', 'MD'), ('northeast', 'Wilmington', 'DE'),
    ('northeast', 'Philidelphia', 'PA'), ('northeast', 'Newark', 'NJ'), ('northeast', 'New York', 'NY');

INSERT INTO Schedule (origin, termination, originTime, termTime, route) VALUES
	(1, 34, TIME '7:55:00', TIME '10:46:00', 'Acela Express');
    
INSERT INTO ScheduleCities (scheduleId, cityId, time) VALUES
	(4, 29, TIME '08:16:00'), (4, 30, TIME '08:29:00'), (4, 31, TIME '09:14:00'),
    (4, 32, TIME '09:35:00'), (4, 33, TIME '10:29:00');

/* Insert reverse Acela */
INSERT INTO Schedule (origin, termination, originTime, termTime, route) VALUES
	(34, 1, TIME '11:00:00', TIME '13:57:00', 'Acela Express');
    
INSERT INTO Cities (region, name, state) VALUES ('northeast', 'Metropark', 'NJ');

INSERT INTO ScheduleCities (scheduleId, cityId, time) VALUES
	(5, 33, TIME '11:14:00'), (5, 35, TIME '11:29:00'), (5, 32, TIME '12:10:00'),
    (5, 31, TIME '12:32:00'), (5, 30, TIME '13:14:00'), (5, 29, TIME '13:27:00');
