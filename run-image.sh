#!/usr/bin/env bash

docker run --rm \
	-v $(pwd)/data:/data \
	h3apspac3/opendata-validator /data/opendata.json /data/opendata_scores.json