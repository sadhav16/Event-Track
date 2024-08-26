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

---

## Add Contestant (POST)

**To add a new contestant to the event, use the following endpoint:**

`POST /api/hart`

**Request Body:** JSON

## Update Contestant (PUT)

**To update an existing contestant's details, use the following endpoint:**

`PUT /api/contestants/{id}`

**Request Body:** JSON

## Delete Contestant (DELETE)

**To delete a contestant from the event, use the following endpoint:**

`DELETE /api/hart/{id}`

## Get All Contestants (GET)

**To retrieve all contestants, use the following endpoint:**

`GET /api/hart`

## Auto Generate Contestants (POST)

**To automatically generate a list of contestants, use the following endpoint:**

`POST /generate`

**Request Parameters:**

- **`count`**: The number of contestants to generate. Optional. Defaults to `5000`.

**Response:** JSON



## Running the Application
To run the program, execute the following file:

Path: *hartRace/src/main/java/com/hartsad/hartRace/HartRaceApplication.java*
