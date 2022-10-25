FROM open-liberty:latest
#FROM --platform=linux/arm64 openliberty/arm

COPY server.xml /opt/ol/wlp/usr/servers/defaultServer/server.xml
COPY ./build/libs/liberty-db2-acr.war /opt/ol/wlp/usr/servers/defaultServer/apps/liberty-db2-acr.war
COPY ./build/dependencies/db2jcc.jar /opt/ol/wlp/usr/shared/db2jcc.jar