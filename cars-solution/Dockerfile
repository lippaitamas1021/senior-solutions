#FROM adoptopenjdk:16-jre-hotspot
#RUN mkdir /opt/app
#COPY target/cars-solution-0.0.1-SNAPSHOT.jar /opt/app/cars-solution.jar
#CMD ["java", "-jar",  "/opt/app/cars-solution.jar"]
#
 FROM adoptopenjdk:16-jre-hotspot as builder
 WORKDIR application
 COPY target/cars-solution-0.0.1-SNAPSHOT.jar cars-solution.jar
 RUN java -Djarmode=layertools -jar cars-solution.jar extract
#
# FROM adoptopenjdk:16-jre-hotspot
# WORKDIR application
# COPY --from=builder application/dependencies/ ./
# COPY --from=builder application/spring-boot-loader/ ./
# COPY --from=builder application/snapshot-dependencies/ ./
# COPY --from=builder application/application/ ./
# ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]