FROM adoptopenjdk:11-jre-hotspot
WORKDIR /opt/app
ADD target/*.jar /opt/app/employees.jar
CMD ["java", "-jar", "/opt/app/employees.jar"]
