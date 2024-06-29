docker stop account-payment-api-21
docker rm account-payment-api-21
docker rmi account-payment-api-21

cp ../app/target/account-payment-api-21-0.0.1-SNAPSHOT.jar account-payment-api-21.jar

docker build -t account-payment-api-21 .
