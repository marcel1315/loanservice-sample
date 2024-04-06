#!/bin/sh

docker-compose -p loanservice-app -f ./docker-compose-app.yml down
docker-compose -p loanservice-db -f ./docker-compose-db.yml down

echo "Removed network: $(docker network rm loanservice-net)"

# App Version
VERSION='1.0.0'

cd ..
ROOT_PATH="$(pwd)"
echo $ROOT_PATH

echo 'api docker image remove...'
docker rmi loanservice-api:$VERSION
echo 'api docker image remove... Done'

echo 'nginx docker image remove...'
docker rmi loanservice-nginx:$VERSION
echo 'nginx docker image remove... Done'