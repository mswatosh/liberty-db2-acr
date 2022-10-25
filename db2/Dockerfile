FROM --platform=linux/amd64 ibmcom/db2 

ADD docker-entrypoint.sh /
RUN chmod a+x /docker-entrypoint.sh

RUN mkdir /var/custom
COPY var/custom/createschema.sh /var/custom/createschema.sh
RUN chmod a+x /var/custom/createschema.sh

ENV LICENSE=accept
ENV DB2INSTANCE=db2inst1
ENV DB2INST1_PASSWORD=password
ENV DBNAME=testdb
ENV AUTOCONFIG=false
ENV ARCHIVE_LOGS=false

ENTRYPOINT ["/docker-entrypoint.sh"]