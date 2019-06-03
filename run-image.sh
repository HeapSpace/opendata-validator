#!/usr/bin/env bash

docker run --rm \
	-v $(pwd)/data:/data \
	heapspace/opendata-validator /data/opendata.json /data/opendata_scores.json