# Chemondis

## Data Engineering Challenge
This project implements a very simple ETL.

There is a main application built with Scala, which imports data from an endless source. After performing neccesary ETL, the process loads the User data into Postgres table.

## Running

Before being able to run the application we will need to install some dependencies:
- JDK 8
- SCALA

## Development

In order to do development related tasks, we will need to also install `docker` and `docker-compose`.

The following steps will be used to set up the environment:
1. Run `docker-compose up` 
2. Run  the following DDL to step up the tables:  sql/ddl/createTables.sql


## Part1 

The saveToDatabase() method has been implemented in src/main/scala/com/etl/challenge.scala. This method transforms the data obtained from generateRandomData() method and saves the data into three tables as following:

* user 
* userLogin
* activity

Exception handling has been enabled in the code for graceful degradation of the application.


## Part2  

The file `sqlSolutions.sql` in the path : sql/ddl/ provides the efficient SQL queries. Some key optimisations used while implementing the SQL solutions are:
* Windowing Functions such as Row_num
* count(1) instead of count(*)


