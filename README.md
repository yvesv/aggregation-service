# Aggregator Service
This awesome web service provides REST API for aggregating all information related to a single power of attorny.
  - Get aggregated details by id (/aggregated/{id})

**Java 11, Spring-Boot & Maven
**Application runs on:** http://localhost:8082

# Features:
  - Implements completable futures to handle requests async to improve speed.
  - Method used to handle errors gracefully is returning empty objects. 
  - Use of the MapStruct api to map objects from request to data transfer objects.
  - Contains a test example. A mock mvc to test the controller.
  
# Enjoy!