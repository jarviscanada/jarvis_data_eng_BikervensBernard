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

(50-100 words)
What does this app do? What technoglies your have used? (JDBC, PSQL, MVN, etc..)


# Implementation
## ER Diagram
- Java Libraries and Tools
    - Maven
    - JDBC
    - JUnit 
- Design Principles
    - DRY
    - KISS
    - ...


## Design Patterns
Discuss DAO and Repository design patterns (150-200 words)

# Test
How you test your app against the database? (e.g. database setup, test data set up, query result)
- Manual testing
- IDE debugger
- JUnit

# Deployment/Delivery
- JVM for Java program
- Github for SCM
- Distribute app using Docker images

