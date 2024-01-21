FROM eclipse-temurin:17-jre
VOLUME /tmp

RUN apt-get update && apt-get install -y curl
RUN groupadd -r appgroup && useradd -r -g appgroup appuser && chown -R appuser:appgroup /tmp

ARG JAR_FILE=build/libs/*.jar
COPY /build/libs/franchises-0.0.1.jar app.jar

RUN sh -c 'touch /app.jar'

ENV TZ=America/Bogota
ENV JAVA_OPTS=" -XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -XX:TieredStopAtLevel=1 -XX:+UseG1GC"

USER appuser
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=$(profile) -jar app.jar" ]