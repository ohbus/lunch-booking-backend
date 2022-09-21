FROM amazoncorretto:17

LABEL maintainer="Subhrodip Mohanta <hello@subho.xyz>"
LABEL artifact="lunch-booking-backend"
LABEL platform="java"
LABEL name="Lunch Booking REST Application"
LABEL org.opencontainers.image.source="https://github.com/ohbus/lunch-booking-backend"

# If the container is launched with re-mapped ports, these 
# ENV vars should be set to the remapped values.
ENV MYSQL_DB_HOST db
ENV MYSQL_DB_PORT 3306
ENV MYSQL_DB_UNAME root
ENV MYSQL_DB_PASSWD root

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

# If you are changing server port, be sure to change this as well
EXPOSE 8080

# Running the application with `prod` profile
ENTRYPOINT [ "java", "-jar", "-Dspring.profiles.active=prod", "/app.jar" ]
