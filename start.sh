#!/usr/bin/env bash

cd ./pay-service
mvn package

cd ./pay-service-start
docker build -t ahxinin/pay-service .

#sudo docker run -p 8081:8081 ahxinin/pay