# Getting Started

### Design

Following considerations taken in design 


<b>Caching</b> : Use Auto expire cache to merged and sanitized data for 10min

<b>Parallelism</b>: Use of parallel threads for processing in increasing the performance

<b>Data Structures</b>: Use of TreeSet like data structures for storing the unique elements

<b>Language Service</b>: Use of language tool to auto correct the facilities keywords and remove the duplicates

<b>JSON Template</b>: Use of JSON Alias to have single Model class for all suppliers. This can be extended further by introducing the template engine for each supplier without any code change 

### Overall Architecture


### Installing Docker

Installation: [Windows](https://docs.docker.com/docker-for-windows/) xor [Mac](https://docs.docker.com/docker-for-mac/).

Java JDK 17 (If running outside docker): [JDK](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)

### Running Locally

You can run the service locally on your machine as a Java process, or inside Docker container.

### Running the Service Locally in Docker

First, create a jar file to run:

```bash
./gradlew build
```

Then start the Docker container via docker-compose:

```bash
docker build -t hotels_data_merge .   
docker run -p 8080:8080 hotels_data_merge 
```

Service should now be available on http://localhost:8080/ or on your docker machine's IP address port 8080 if you are using Docker Toolbox.

### Running the Service as a Java Process

You can also create a runnable jar file that contains the compiled microservice and all it’s dependencies. To create the runnable jar use the following command:

```bash
./gradlew clean build
```

You can then run the jar with the following command:

```bash
java -jar build/libs/hotels_data_merge-0.0.1.jar

```

### Requests

Caching is enabled at the service to be alive for 10 minutes.

Full response.
```bash
curl -s -H "Content-Type: application/json" -X GET 'http://localhost:8080/api/hotel/merge'

```

Filter response based on destinationId and hotelIds
```bash
curl -s -H "Content-Type: application/json" -X GET 'http://localhost:8080/api/hotel/merge?destinationId=5432&hotelIds=SjyX'

```

Filter response based on list of hotelIds
```bash

curl -s -H "Content-Type: application/json" -X GET 'http://localhost:8080/api/hotel/merge?destinationId=5432,123'

```


### Configuration and Logging

This service has 3 configuration files which apply necessary configuration settings in development and production environments. Configuration settings should be replicated in these configuration files.

Which configuration file is selected depends upon the `CONFIGURATION_FILE_NAME` environment variable set in the `.github CLI` file.

The standard configuration ensures that:

* In a 'dev' environment (e.g. developer workstation, or by default) all **business event logs** and **application logs** are simply output on the console for ease of diagnostics.

## Running tests
* Run all the unit core unit tests

        ./gradlew clean test


### Application logging (standard SLF4J logging)

```yaml
logging:
  level: INFO
  appenders:
   - type: grappler
     logFormat: "%-5p [%d{ISO8601,UTC}] [%t] %c: %m%n%rEx"
     environmentName: prod