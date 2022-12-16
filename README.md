# LIDP Challenge (Javier's Take)

I decided to write my answer here for safekeeping, 
highlighting what I did, what I missed, changes 
made, etc. So let's begin!

## Everything's centered around this

Unfortunately I couldn't finish all requirements 
and there were some changes I had to make, but 
they were centered around my main assumption: 
Docker/Kubernetes won't be pretty.

Due to my lack of knowledge of Docker and 
Kubernetes my main assumption was that I had to 
make the challenge work by giving more priority to 
my unknowns.

## Requirements

### [x] Expose the getFares and getFare service methods over HTTP.

This was a bit complicated, but manageable. First problem I found was that I 
couldn't easily add Spring to LIDP's project; it required having Intellij Ultimate.

But a solution was [Spring Initializr](https://start.spring.io/). 
With this wizard I created a project and replaced the main app with it. The idea 
was to start adding the FareCalculator files to a project that would have all 
the components, and it worked. These were the selected settings I used on the initializr:

* **`Project`**: Gradle - Kotlin
* **`Language`**: Java
* **`Spring Boot`**: 3.0.0
* **`Project Metadata`**: Custom; have it your way, but make it as close as the original project.
* **`Packaging`**: Jar
* **`Java`**: 17
* **`Dependencies`**: Spring Web

With this you'll have a basic project that has Spring and Gradle set up. 

After that I had to make some changes to make the app work with the little time I had and 
that's it. With my project you'll be able to access 2 routes:

1. **`fares`** (Plural): gets all fares from the database.

[localhost:8080/fares](http://localhost:8080/fares)

2. **`fare`** (Singular): gets the fare by its parameters. Inserts the fare in database if not found. Uses default data if called with no parameters.

[localhost:8080/fare](http://localhost:8080/fare)

[localhost:8080/fare?date=02/03/2023&miles=2&row=2](http://localhost:8080/fare?date=02/03/2023&miles=2&row=2)

* **`date`**: date in **MM/dd/yyyy** format
* **`miles`**: miles in number format
* **`row`**: seat row in number format

### [x] Connect the application to a database of your choosing.

For this project I used MariaDB, since that's what I had installed at the moment. I imported the JDBC plugin

[MariaDB Connector](https://mariadb.com/docs/server/connect/programming-languages/java/install/)

I kept the port as 3306 and set **123** as the root password. I created a db called **`lidp`** and ran 
this query to create the **`fares`** table:

```
CREATE TABLE fares (  
    fare_id BigInt(20)  NOT NULL AUTO_INCREMENT,
    fare_date           Date NOT NULL,
    fare_distance_miles Double NOT NULL,
    fare_seat_row       Integer(11) NOT NULL,
    fare_amount         Double NOT NULL,
    PRIMARY KEY (
        fare_id
    )
) ENGINE=InnoDB AUTO_INCREMENT=12 ROW_FORMAT=DYNAMIC DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
ALTER TABLE fares COMMENT = '';
```

### [x] Implement the data access layer to allow calculated fares to persist in the database and for users to query all stored fares.

The previous requirement covers the basic of setting the database up, so yeah, data persistence will be a thing here.

### [x] Ensure the FareCalculator can be compiled as a fat jar using Gradle.

Luckily, Spring Initializr did good for this. Just pop up a console/terminal and do this

`gradle clean`

Then

`gradle build`

And that's it. You should see the jar file under `build/libs`

### [ ] Create a Dockerfile used to build a container image of the FareCalculator.

This is where I failed and brought shame to family. 

I could create a container for MariaDB 
(using `mariadb:latest` image) and I could access the database, but for some reason 
I couldn't connect to it if the app was also on a container. I could connect 
to the db if I were debugging/running intellij, but for some reason I get a 
"Connection Refused" as soon as I containerize the app.

I dunno, maybe the problem's with the dockerfile, or maybe I'm missing extra 
stuff from Spring, but I couldn't find any posts or documentation that I could relate to.

## Changes

1. Main change would be the project replacement.
2. I made changes to some files and classes. Did them to combat the time constraint.
3. I also changed the typing on some functions and datapoints.

## Improvements

1. **Validations**: Currently there are no validations. A good update would be to validate the parameters and get some exceptions if something happens when interacting with the database.
2. **Unit testing**: Some unit testing would be good for an "API".
3. **UI**: Currently the app only works by making HTTP requests, so a good update would be a basic web page with its fare inputs

## Conclusion

So yeah, got stuck on the container part, but it's one of those instances where 
if I had more time on my unknowns, I could've pulled it off. Or in a real case scenario, 
there could be a high chance a teammate could have the answer.