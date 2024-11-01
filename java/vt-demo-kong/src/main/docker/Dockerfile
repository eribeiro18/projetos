FROM openjdk:21-slim

ARG VERSION

WORKDIR /app

COPY maven/account-payment-api-21-${VERSION}.jar /app/account-payment-api-21-${VERSION}.jar

ENTRYPOINT ["java", "-jar", "account-payment-api-21-"+${VERSION}+.jar]