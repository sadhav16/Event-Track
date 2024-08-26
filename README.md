## Important:
Before Running the Main Application

Please ensure that you have added the necessary database connection details in the application.properties file located at:

Path: *hartRace/src/main/resources/application.properties*

This configuration is crucial for the application to establish a connection with the database.

## Getting Started
Root URL: http://localhost:8080/api/hart

## Table of Contents
1. [Add Contestant (POST)](#add-contestant-post)
2. [Update Details (PUT)](#update-contestant-put)
3. [Delete Contestant (DELETE)](#delete-contestant-delete)
4. [Get All Contestants (GET)](#get-all-contestants-get)
5. [Auto Generate Contestants (POST)](#auto-generate-contestants-post)
6. [Get Event Contestants (GET)](#get-event-contestants-get)
7. [Get Contestants by Gender (GET)](#get-contestants-by-gender-get)
8. [Simulate Event (GET)](#simulate-event-get)
9. [Top Medalist (GET)](#top-medalist-get)
10. [Top Points Contestant (GET)](#top-points-contestant-get)
11. [Top Nation (GET)](#top-nation-get)
12. [Top N Nations (GET)](#top-n-nations-get)
13. [Nation Rankings (GET)](#nation-rankings-get)
14. [Simulate All Events (GET)](#simulate-all-events-get)

---

## Add Contestant (POST)

**To add a new contestant to the event, use the following endpoint:**

`POST /`

**Request Body:** JSON

## Update Contestant (PUT)

**To update an existing contestant's details, use the following endpoint:**

`PUT /{id}`

**Request Body:** JSON

## Delete Contestant (DELETE)

**To delete a contestant from the event, use the following endpoint:**

`DELETE /{id}`

## Get All Contestants (GET)

**To retrieve all contestants, use the following endpoint:**

`GET /`

## Auto Generate Contestants (POST)

**To automatically generate a list of contestants, use the following endpoint:**

`POST /generate`

**Request Parameters:**

- **`count`**: The number of contestants to generate. Optional. Defaults to `5000`.

**Response:** JSON

## Get Contestants by Item (GET)

**To retrieve contestants by item, use the following endpoint:**

`GET /api/hart/getitemcontestents/{item}`
**Response:** JSON

## Get Event Contestants (GET)

**To retrieve contestants for a particular event, use the following endpoint:**

`GET /api/hart/geteventparticipants/{event}`

**Response:** JSON

## Get Contestants by Gender (GET)

**To retrieve contestants by gender, use the following endpoint:**

`GET /api/hart/getparticipantsongender/{gender}`

**Response:** JSON

## Simulate Event (GET)

**To simulate/get all contestants for a particular event, use the following endpoint:**

`GET /api/hart/simulateevent`
**Request Parameters:**
- **event**: The event to simulate. Required.
- **item**: The item to simulate. Required.
- **gender**: Optional. The gender to filter by.

**Response:** JSON

## Simulate Event (GET)

**To simulate/get all contestants for a particular event, use the following endpoint:**

`GET /api/hart/simulateevent`

**Request Parameters:**
- **event**: The event to simulate. Required.
- **item**: The item to simulate. Required.
- **gender**: Optional. The gender to filter by.

**Response:** JSON


## Top Medalist (GET)

**To retrieve the contestant with the top number of medals, use the following endpoint:**

`GET /api/hart/topmedalist`
**Response:** JSON

## Top Points Contestant (GET)

**To retrieve the top contestant based on points for a specific gender, use the following endpoint:**

`GET /api/hart/topcontestant/{gender}`
**Response:** JSON

## Top Nation (GET)

**To retrieve the nation with the highest number of medals, use the following endpoint:**

`GET /api/hart/topnation`
**Response:** JSON

## Top N Nations (GET)

**To retrieve the top N nations with the highest number of medals, use the following endpoint:**

`GET /api/hart/topnations`

**Request Parameters:**
- **number**: The number of top nations to retrieve. Optional. Defaults to 1.
- **event**: Optional. The event to filter nations by.

**Response:** JSON

## Nation Rankings (GET)

**To retrieve nation rankings based on specific criteria, use the following endpoint:**

`GET /api/hart/nationrankings`
**Request Parameters:**
- **type**: The type of statistics to retrieve. Required.
- **criteria**: The criteria for ranking. Required.

**Response:** JSON

## Simulate All Events (GET)

**To simulate all events and add contestants, use the following endpoint:**

`GET /api/hart/simulateall`
**Response:** JSON

---

## Running the Application
To run the program, execute the following file:

Path: *hartRace/src/main/java/com/hartsad/hartRace/HartRaceApplication.java*
