 FROM adoptopenjdk:16-jre-hotspot as builder
 WORKDIR application
 COPY target/bikesharing-0.0.1-SNAPSHOT.jar bikesharing.jar
 RUN java -Djarmode=layertools -jar bikesharing.jar extract
