# Glassfish 4.1 with JDK 8 Docker image
# Based on: https://github.com/aws/aws-eb-glassfish-dockerfiles/blob/aafcfc5e812dfb9b998105d3ca9da1b7f10664e1/4.0-jdk7/Dockerfile

FROM        java:8-jdk

MAINTAINER  Almir Dzinovic <almirdzin@gmail.com>

ENV         JAVA_HOME         /usr/lib/jvm/java-8-openjdk-amd64
ENV         GLASSFISH_HOME    /usr/glassfish4
ENV         PATH              $PATH:$JAVA_HOME/bin:$GLASSFISH_HOME/bin

RUN         apt-get update && \
            apt-get install -y curl unzip zip inotify-tools && \
            rm -rf /var/lib/apt/lists/*

RUN         curl -L -o /tmp/glassfish4.zip http://download.java.net/glassfish/4.1.1/release/glassfish-4.1.1.zip && \
            unzip /tmp/glassfish4.zip -d /usr && \
            rm -f /tmp/glassfish4.zip && \
            find /usr/glassfish4/glassfish/ -name "*.bat" -exec rm -f {} \;



EXPOSE      3700

WORKDIR     /usr/glassfish4

ADD target/PeopleCrudCore.jar $WORKDIR
ADD PeopleCrudLibrary-1.0-SNAPSHOT.jar /usr/glassfish4/glassfish/lib/
ADD postgresql-9.1-901-1.jdbc4.jar /usr/glassfish4/glassfish/lib/

#iniciar Glassfish, implantar PeopleCrudCore.jar, fix para impedir que esse container feche após a implantação
CMD asadmin start-domain && asadmin deploy PeopleCrudCore.jar && tail -f /dev/null
