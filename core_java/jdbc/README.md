# Order of operations
These commands are for linux/Mac, changes will need to made if you are running this in Microsoft Windows.

## Prerequisites
Docker is installed
psql client is installed

## Actions

### Running PostgreSQL
1. Pull Docker Image
`docker pull postgres`

2. Build data directory
`mkdir -p ~/srv/postgres`

3. Run docker image
`docker run --rm --name lil-postgres -e POSTGRES_PASSWORD=password -d -v $HOME/srv/postgres:/var/lib/postgresql/data -p 5432:5432 postgres`

### Creating stored procedure
1. `psql -h localhost -U postgres -f stored_proc.sql`



# Introduction to the project

The Jarvis Securities Team wants to develop a trading platform REST API (which is your next project [](https://www.notion.so/12c2e58517d543889d35ca337752aeff) ). As a junior developer, you will be involved in the entire [SDLC](https://www.notion.so/SDLC-d104522f8afb4edc9224fdd25348bf6a) . However, your team lead wants you to master core java before joining the trading app project. Core Java is the foundation of data engineering since most backend services and big data frameworks (e.g. Hadoop, Spark) are developed in Java or JVM-based languages.

# Design

As a junior developer, you will develop two Java applications:

- ...

# Implementation

- Java Libraries and Tools
    - Maven
    - JDBC
    - JSON libraries
    - HTTP clients
    - JUnit & Mockito
- Design Principles
    - DRY
    - KISS

# Test

- Manual testing
- IDE debugger
- JUnit

# Deployment/Delivery

- JVM for Java program
- Github for SCM
- Distribute app using Docker images

