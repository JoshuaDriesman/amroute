USE amroute;

INSERT INTO routes (name) VALUES ('Capitol Limited');

INSERT INTO equipment (configuration, series) VALUES ('Sleeping Car', 'Superliner I'),
	('Sleeping Car', 'Superliner II'), ('Dining Car', 'Superliner I'), ('Sightseer Lounge', 'Superliner II'),
    ('Coach', 'Superliner I'), ('Baggage', 'Superliner I');
    
INSERT INTO routeequipment (routeName, equipmentId) VALUES ('Capitol Limited', 1),
	('Capitol Limited', 2), ('Capitol Limited', 3), ('Capitol Limited', 4), ('Capitol Limited', 5),
    ('Capitol Limited', 6);
    
INSERT INTO cities (region, name, state) VALUES ('southeast', 'Washington', 'DC'), ('southeast', 'Rockville', 'MD'),
	('southeast', 'Harpers Ferry', 'WV'), ('southeast', 'Martinsburg', 'WV'), ('southeast', 'Cumberland', 'MD'), 
    ('northeast', 'Connellsville', 'PA'), ('northeast', 'Pittsburgh', 'PA'), ('midwest', 'Alliance', 'OH'), ('midwest', 'Cleveland', 'OH'),
    ('midwest', 'Elyria', 'OH'), ('midwest', 'Sandusky', 'OH'), ('midwest', 'Toledo', 'OH'), ('midwest', 'Waterloo', 'IN'), 
    ('midwest', 'Elkhart', 'IN'), ('midwest', 'South Bend', 'IN'), ('midwest', 'Chicago', 'IL');
    
INSERT INTO schedule (origin, termination, originTime, termTime, route) VALUE 
	(1, 14, TIME '16:05:00', TIME '08:45:00', 'Capitol Limited');
    
INSERT INTO schedulecities (scheduleId, cityId, time) VALUES (1, 2, TIME '16:29:00'),
	(1, 3, TIME '17:16:00'), (1, 4, TIME '17:45:00'), (1, 5, TIME '19:17:00'), (1, 6, TIME '21:47:00'),
    (1, 7, TIME '23:48:00'), (1, 8, TIME '01:39:00'), (1, 9, TIME '02:53:00'), (1, 10, TIME '03:29:00'),
    (1, 11, TIME '04:02:00'), (1, 12, TIME '05:08:00'), (1, 13, TIME '06:36:00'), (1, 14, TIME '07:29:00'),
    (1, 15, TIME '07:51:00');