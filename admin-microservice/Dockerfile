FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]


#FROM amazoncorretto:11-alpine-jdk
#WORKDIR /app/admin-microservice
#COPY pom1.xml ../pom.xml
#COPY .mvn/ ./.mvn
#COPY mvnw pom.xml ./
##RUN ./mvnw dependency:go-offline
#RUN ./mvnw dependency:resolve
#COPY src ./src
#CMD ["./mvnw", "spring-boot:run"]






#FROM amazoncorretto:11-alpine-jdk
#COPY target/*.jar public-microservice.jar
#ENTRYPOINT ["java","-jar","/public-microservice.jar"]