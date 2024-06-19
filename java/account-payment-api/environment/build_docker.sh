docker stop account-payment-api
docker rm account-payment-api 
docker rmi account-payment-api

cp ../app/target/account-payment-api-0.0.1-SNAPSHOT.jar account-payment-api.jar

docker build -t account-payment-api .
