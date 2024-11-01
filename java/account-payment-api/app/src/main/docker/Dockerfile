FROM openjdk:17-alpine

ARG VERSION

WORKDIR /app

COPY maven/account-payment-api-${VERSION}.jar /app/account-payment-api-${VERSION}.jar

ENTRYPOINT ["java", "-jar", "account-payment-api-"+${VERSION}+.jar]