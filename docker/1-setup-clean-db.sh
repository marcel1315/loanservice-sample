#!/bin/sh

# Make Docker network
NETWORK_NAME="loanservice-net"

network_exists=$(docker network ls --filter name=^${NETWORK_NAME}$ --format "{{ .Name }}")

if [ "$network_exists" == "$NETWORK_NAME" ]; then
    echo "Network $NETWORK_NAME exists."
else
    echo "Creating network $NETWORK_NAME..."
    docker network create $NETWORK_NAME
    echo "Network $NETWORK_NAME created."
fi

# Make DB
docker-compose -p loanservice-db -f ./docker-compose-db.yml down
docker-compose -p loanservice-db -f ./docker-compose-db.yml up -d