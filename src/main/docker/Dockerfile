FROM openjdk:8-jre-alpine
LABEL maintainer="igor.spasic@gmail.com"

COPY build/distributions/*.tar /app.tar
RUN tar -xf /app.tar && \
	mv /opendata-validator-* /app && \
	rm app.tar

RUN mkdir /data

ENTRYPOINT [ "/app/bin/opendata-validator" ]