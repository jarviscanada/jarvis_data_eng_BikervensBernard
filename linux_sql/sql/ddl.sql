--Assume the database is already created
--1. (optional) switch to `host_agent`
\c host_agent;

SET TIMEZONE='UTC';

--2. create `host_info` table if not exist
CREATE TABLE IF NOT EXISTS PUBLIC.host_info (
    id SERIAL NOT NULL PRIMARY KEY,
    hostname  VARCHAR NOT NULL UNIQUE,
    cpu_number SMALLINT Not NULL,
    cpu_architecture VARCHAR NOT NULL,
    cpu_model VARCHAR NOT NULL,
    cpu_mhz REAL NOT NULL,
    L2_cache INTEGER NOT NULL,
    total_mem bigint NOT NULL,
    "timestamp" TIMESTAMP NOT NULL
);

--3. create `host_usage` table if not exist
CREATE TABLE IF NOT EXISTS PUBLIC.host_usage (
    "timestamp" TIMESTAMP NOT NULL,
    host_id SERIAL REFERENCES host_info(id),
    memory_free INTEGER NOT NULL,
    cpu_idle INTEGER NOT NULL,
    cpu_kernel INTEGER NOT NULL,
    disk_io INTEGER NOT NULL,
    disk_available INTEGER NOT NULL
);
\d public.host_info
\d public.host_usage
