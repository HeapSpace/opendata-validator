#!/usr/bin/env bash

./gradlew clean assembleDist

docker build . -f src/main/docker/Dockerfile -t h3apspac3/opendata-validator