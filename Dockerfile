FROM openjdk:8-jre-alpine
RUN mkdir -p /usr/src/apps/roulette
COPY target/roulette-0.0.1-SNAPSHOT.jar /usr/src/apps/roulette
WORKDIR /usr/src/apps/roulette
CMD ["nohup", "java", "-jar", "roulette-0.0.1-SNAPSHOT.jar", "&"]