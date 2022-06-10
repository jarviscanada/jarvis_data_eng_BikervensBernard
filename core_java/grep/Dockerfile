FROM openjdk:8-jdk-alpine

RUN apk add --no-cache bash

COPY target/grep*.jar /usr/local/app/grep/lib/grep.jar

# (in order to debug/examine state of the file system)
# you can override the entry point to change it to a shell:
# docker run -it --entrypoint=/bin/bash myimagename
ENTRYPOINT ["java","-jar","/usr/local/app/grep/lib/grep.jar"]

CMD [""]