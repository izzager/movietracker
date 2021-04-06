# Movie Tracker Application

## Description

MovieTracker is an application that is used to keep track of the movies you want to watch and 
the ones you have already watched. It allows you to leave reviews and ratings of the movies you watched.
Implemented application supports security (authorization).

## How to run application using Docker

1. Write in terminal:
```
docker run -p 5432:5432 --name MovieTracker -e POSTGRES_PASSWORD=pass -d postgres
docker exec -it MovieTracker ./bin/bash
psql -U postgres
CREATE DATABASE movietracker;
```

It's run Docker container and created database.

2. Download and unzip project archive.

3. Open project in IntelliJ IDEA. Edit startup configurations 
   with using environment variables:

```
database.username=postgres;database.password=pass;hibernate.ddl-auto=create
```
   
Then press "Apply" or "OK". It is required for actions with database.

4. Run application in IntelliJ IDEA. Now application is running and ready to work. 
   Database was filled with data about movies and users.
   There are 4 movies with ids 1-4 and 2 users with usernames "admin" and "user1" 
   and passwords "pass" and "pass". You can use this information for log in and testing.
   
5. Download Postman Collection and import it in Postman. There are several 
   prepared requests for easier testing of the application. The user's login details are specified 
   in the settings: username "admin" and password "pass", but you can change it anytime.

6. Application is ready to work and be tested!
