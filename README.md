# OpenData validator

Update opendata database:

```shell
./db-update.sh
```

Build docker image (if not published):

```shell
./build-image.sh
```

Run validator:

```shell
./run-image.sh
```

The validation takes some significant time!

The validation result is saved in `./data/opendata_scores.json`
