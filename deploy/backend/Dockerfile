FROM adoptopenjdk:11-jre-hotspot
RUN mkdir /opt/app
COPY ../backend/target/streamapi-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/japp.jar"]