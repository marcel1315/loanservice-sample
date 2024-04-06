#!/bin/sh

# App Version
VERSION='1.0.0'

cd ..
./gradlew clean build -x test

ROOT_PATH="$(pwd)"
echo $ROOT_PATH

echo 'api docker image build...'
cd "$ROOT_PATH"/api && docker build -t loanservice-api:$VERSION .
echo 'api docker image build... Done'

echo 'nginx docker image build...'
cd "$ROOT_PATH"/nginx && docker build -t loanservice-nginx:$VERSION .
echo 'nginx docker image build... Done'