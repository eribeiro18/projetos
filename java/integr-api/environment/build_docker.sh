docker stop integr-api
docker rm integr-api
docker rmi integr-api

cp ../app/target/integr-api-0.0.1-SNAPSHOT.jar integr-api.jar

docker build -t integr-api .
