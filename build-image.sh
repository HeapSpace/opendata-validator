#!/usr/bin/env bash

./gradlew clean assembleDist

docker build . -f src/main/docker/Dockerfile -t heapspace/opendata-validator