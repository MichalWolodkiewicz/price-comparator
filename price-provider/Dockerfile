FROM ubuntu:18.04
RUN apt-get update && apt-get install -y openjdk-8-jre
COPY target/price-provider-0.0.1-SNAPSHOT.jar /tmp/app.jar
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/tmp/app.jar"]
