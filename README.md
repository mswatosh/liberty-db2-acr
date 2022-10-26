# Db2 failover options using Liberty
This is a collection of Docker environments demonstrating Db2 failover options with Liberty. It consists of a simple Servlet application which returns the JDBC url of the database it's connection points to, and a pair of Db2 containers for failover.

## Db2 Client Reroute

Db2 client reroute is the simplest failover option, allowing rerouting to occur only on the first connection request.
1. Build the application and start the containers  
`./gradlew libertyPackage && docker-compose -f reroute.yml build && docker-compose -f reroute.yml up`

2. After all containers have completed initializing, access the endpoint  
`http://localhost:9080/liberty-db2-acr/example`  
It should point to the `db2b` container  
`URL:jdbc:db2://db2b:50000/TESTDB`

3. Stop the containers and uncomment the the db2a container in `reroute.yml`

4. Build and start the containers again  
`docker-compose -f reroute.yml build && docker-compose -f reroute.yml up`

5. After all containers have completed initializing, access the endpoint  
`http://localhost:9080/liberty-db2-acr/example`  
It should point to the `db2a` container  
`URL:jdbc:db2://db2a:50000/TESTDB`

## Db2 Client Affinities

Client affinities allows for failover at runtime, on new connection requests.
1. Build the application and start the containers  
`./gradlew libertyPackage && docker-compose -f client-affinities.yml build && docker-compose -f client-affinities.yml up`

2. After all containers have completed initializing, access the endpoint  
`http://localhost:9080/liberty-db2-acr/example`  
It should point to the `db2a` container  
`URL:jdbc:db2://db2a:50000/TESTDB`

3. Stop the db2a container
`docker stop liberty-db2-acr-db2a-1`

4. Access the endpoint  
`http://localhost:9080/liberty-db2-acr/example`  
It should point to the `db2b` container  
`URL:jdbc:db2://db2b:50000/TESTDB`

5. Restart the db2a container
`docker start liberty-db2-acr-db2a-1`

6. After the container has completed initializing, access the endpoint  
`http://localhost:9080/liberty-db2-acr/example`  
Failback should have occurred, so it should point to the `db2a` container  
`URL:jdbc:db2://db2a:50000/TESTDB`
