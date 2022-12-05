#FROM openjdk:8u191-jre-alpine3.8
#FROM amazoncorretto:11-alpine3.16-jdk
FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine

# Workspace
WORKDIR /app

# Add .jar from host into this image
ADD target/flipkart_grid-docker.jar            flipkart_grid-docker.jar
ADD target/flipkart_grid-docker-tests.jar      flipkart_grid-docker-tests.jar
ADD target/libs                                libs

# Add resources
ADD src/test/resources                         src/test/resources

# Add suite files
ADD testng1.xml  testng.xml

# Add docker-compose
ADD docker-compose.yml  docker-compose.yml

ENTRYPOINT java -cp flipkart_grid-docker.jar:flipkart_grid-docker-tests.jar:libs/* org.testng.TestNG testng.xml
# ENTRYPOINT java -cp flipkart_grid-docker.jar:flipkart_grid-docker-tests.jar:libs/*
# ENTRYPOINT ["java","Entry"]
# ENTRYPOINT ["java", "-jar", "/flipkart_grid-docker.jar"]