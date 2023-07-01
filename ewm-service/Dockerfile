FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar ewm_service.jar
ENV SPRINGPROFILES=prod,env
RUN apk --no-cache add curl
ENTRYPOINT ["java","-jar","/ewm_service.jar"]