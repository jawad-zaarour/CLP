# Cookie Log Processor - CLP

## Overview

This project is a command-line application designed to process cookie log files and determine the most active cookie for a given date. 
## CLI Usage


    Usage: java -jar clp.jar [-h] -d=<date> -f=<filename>

    Cookie Log Processor with configurable output
      -f, --file=<filename>
                                  Path to the log file
      -d, --date=<date>
                                  Date in format yyyy-MM-dd
      -h, --help                  
                                  Print usage help

## Setup
| Dependency | Usage                  | Version |
|------------|------------------------|---------|
| Java       | Programming Language   | JDK 21  |
| Maven      | Dependency Management  | 3.9.8   |
| Picocli    | Command-line Interface | 4.7.5   |

## How to Build

**Compile the project**:
```sh
mvn clean install
```

**Run the application**:
   (To find the most active cookie for a specific date)
```sh
java -jar clp.jar -d 2018-12-09 -f cookie_log.csv
```

## Docker (Optional)

Create a docker image:
```sh
docker build -t clp .
```
Run the Docker Image:
```sh
docker run --rm clp -d 2018-12-09 -f /app/cookie_log.csv
```