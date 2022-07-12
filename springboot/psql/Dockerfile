FROM postgres:9.6-alpine
COPY init_db.sql /docker-entrypoint-initdb.d/
COPY schema.sql /docker-entrypoint-initdb.d/
