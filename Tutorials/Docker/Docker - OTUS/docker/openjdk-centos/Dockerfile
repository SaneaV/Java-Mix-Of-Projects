FROM bellsoft/liberica-openjdk-centos:11.0.3
ENV PROJECT_ROOT /usr/src/app
WORKDIR $PROJECT_ROOT
COPY ./DockerTutorial.jar DockerTutorial.jar
CMD java -XX:+UseContainerSupport -Xmx512m -Xms512m \
-Dcom.sun.management.jmxremote \
-Dcom.sun.management.jmxremote.port=9010 \
-Dcom.sun.management.jmxremote.local.only=false \
-Dcom.sun.management.jmxremote.authenticate=false \
-Dcom.sun.management.jmxremote.ssl=false \
-Dcom.sun.management.jmxremote.rmi.port=9010 \
-Djava.rmi.server.hostname=localhost \
-jar DockerTutorial.jar
