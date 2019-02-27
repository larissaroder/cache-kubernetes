FROM openjdk:11.0.1-jre-slim

ARG jarfile=hazelcast-0.0.1-SNAPSHOT.jar

VOLUME /tmp

ADD target/${jarfile} /opt/cache/app.jar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -DSPRING_PROFILES_ACTIVE=$SPRING_PROFILES_ACTIVE -jar /opt/cache/app.jar"]
