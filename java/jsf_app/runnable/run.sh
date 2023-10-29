cd ../
echo 'Building container....'
docker build -t :v1 .

echo 'Creating docker network...'
docker network rm demo_app
docker network create demo_app

echo 'Wait...Starting application...'
sleep 5

docker run --rm  \
	--name pg_app_demo --network demo_app \
	-e POSTGRES_DB=jsf_application_demo \
	-e POSTGRES_USER=sa \
	-e POSTGRES_PASSWORD=123 \
	-e PGDATA=/var/lib/postgresql/data/pgdata \
	-p 5432:5432 \
	-v /opt/dockerdata/postgres:/var/lib/postgresql/data \
	-d postgres

echo 'Waiting 10 seconds....'
sleep 10

echo 'Publish application....'
docker run --rm --name  --network demo_app \
-p 8085:8080 \
-p 9995:9990 \
-d :v1