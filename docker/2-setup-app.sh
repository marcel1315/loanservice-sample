#!/bin/sh

docker-compose -p loanservice-app -f ./docker-compose-app.yml down
docker-compose -p loanservice-app -f ./docker-compose-app.yml up -d