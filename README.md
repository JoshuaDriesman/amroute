## About Amroute
Amroute is a project I developed for CS3200 Database Design at Northeastern University.
It is a database and front facing application for keeping track of the Amtrak train routes
in the US. It supports CRUD operations and basic search functionality. My goal with this project
was not only to learn database design but to also become familiar with some Java web development,
something that has intimidated me in the past. I also chose this project because I like trains.

![Trains](http://vignette3.wikia.nocookie.net/asdfmovie/images/1/1e/I_like_trains_boy.jpg/revision/latest?cb=20130131143929)

## Set Up/Installation

First, make sure you have the following software:

- Java 8 or higher
- Maven
- MySQL

Next, you need to set up the MySQL server with the required tables and stored procedures:

1. Set up database: ```$ mysql -u root -p < src/main/resources/sql/createdatabase.sql```.
2. Log in to MySQL and add new user: ```mysql> CREATE USER 'user'@'host' IDENTIFIED BY 'password';```
3. Grant privileges to that user on the new database:
```GRANT ALL ON 'amroute'.* TO 'user'@'host'; FLUSH PRIVILEGES''```
4. Insert test data: ```$ mysql -u root -p < src/main/resources/sql/insertTestData.sql```
5. Create stored procedures: ```$ mysql -u root -p < src/main/resources/sql/storedProcedures.sql```
6. Update src/main/resources/application.properties with database connection information.
    1. Set ```spring.datasource.url``` to database host and name.
    2. Set ```spring.datasource.username``` to the database user's username.
    3. Set ```spring.datasource.password``` to the password for the database user.
7. Run  ```$ mvn -DskipTests install``` to build the project.
8. Run the project with ```java –jar ./target/amroute-0.0.1-SNAPSHOT```


&copy; Joshua Driesman 2015 - 2016