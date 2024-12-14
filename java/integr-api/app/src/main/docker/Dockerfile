FROM openjdk:21-slim

ARG VERSION

WORKDIR /app

COPY maven/integr-api-21-${VERSION}.jar /app/integr-api-21-${VERSION}.jar

ENTRYPOINT ["java", "-jar", "integr-api-21-"+${VERSION}+.jar]