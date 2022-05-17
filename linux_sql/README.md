
# Introduction

A CentOS 7 based Linux node cluster agent
This tool's goal is to keep track of each node's hardware characteristics and real-time resource utilisation (e.g. CPU/Memory) for future resource planning purposes (e.g. add/remove servers that might be too slow or under used). 
The data will be saved in a PostgreSQL relational database management system (RDBMS). 
The SQL queries will answer business questions such as 'show average memory usage in percentage over 1 minute intervals for each node'.

# Implementation

I will be in charge of designing and implementing a monitoring tool that will assist a potential business need using 
 - Linux command lines
 - Bash scripts
 - PostgreSQL
 - Docker

### **Architecture**

![architecture](./assets/architecture.png)

- A `psql` instance is used to persist all the data
- The `nodes` are docker containers sharing the same volume (the data parsist using psql volume)
- The `switch` will do the cluster networking. Each nodes are on a same network connected. In this implementation we use localhost and appropriate ports for testing
- The `host_info.sh` will be installed on 1 host/server/node and collects the host hardware info and insert it into the database. It will be run only once at the installation time.
- The `host_usage.sh` will be installed on every host/server/node and will gather memory/cpu usage. It will be triggered by the `crontab` job every minute.
- The `psql_docker.sh` will be installed on the main host and will start the docker demaon and make sure the containers are running

### **Postgres Database**

host_usage table
| Attribut | Description |
| --- | --- |
| `host_id`| The unique identifier psql db auto-increment| 
| `host_name`| The full name of the node| 
| `host_cpu_number`| The sum of cpu core| 
| `host_cpu_architecture`| The generation| 
| `host_cpu_model`| The manifacturer (e.g Intel(R) Xeon(R) CPU @ 2.30GHz)| 
| `host_cpu_mhz`| The sum, in megahertz, of the actively used CPU| 
| `host_L2_cache`| The sum, in kB, memory bank built into the CPU| 
| `host_tot_memory`| The sum of ram, in kB| 
| `host_timestamp`| Current time in UTC time zone| 

host_info table
| Attribut | Description |
| --- | --- |
| `host_id`| unique identifier psql db auto-increment| 
| `host_timestamp`| Current time in UTC time zone| 
| `host_free_memo`| The amount of memory not being used, in MB| 
| `host_cpu_idel`| in percentage| 
| `host_cpu_kernel`| in percentage| 
| `host_disk_io`| The amount of disk space used| 
| `host_disk_available`| The amount of root directory avaiable disk space available in MB| 

# Test

tested on a single machine instead of a Linux cluster. 
However, should work in a cluster environment, assuming connection and firewalls are set up correctly (done by other teams). For bash scripts, you should test/verify all functionalities manually. You will need to verify the query result against test data (created by developers) for SQL scripts.

# Deployment/DevOps

The agent program is scheduled using `crontab`. Source code is managed by GitHub. Database is provisioned with Docker.

# Quick Start
- Create a psql instance using psql_docker.sh: bash ./scripts/psql_docker.sh create [database's username] [database's password] 
```
bash ./scripts/psql_docker.sh create postgres password
```

<br/>

- Start a psql instance using psql_docker.sh: By default the container's name is `postgres`
```
bash ./scripts/psql_docker.sh start
```

<br/>

- Stop a psql instance using psql_docker.sh: The database will be persisted
```
bash ./scripts/psql_docker.sh stop
```

<br/>

- Create tables using ddl.sql (this is ran during the container's creation. Therefore the create cmd on `psql_docker.sh` will have the same effect): <br/> psql -h [postgres host] -U [postgres username] -d [database] -f [path to ddl.sql]
```
psql -h localhost -U postgres -d host_agent -f ./sql/ddl.sql
```

<br/>

- Insert hardware specs data into the DB using host_info.sh: <br/> ./scripts/host_usage.sh [postgres host] [postgres port] [database] [postgres username] [postgres password]
```
./scripts/host_usage.sh localhost 5432 host_agent posgress password
```

<br/>
<br/>
<br/>
<br/>

- Insert hardware usage data into the DB using host_usage.sh
- Crontab setup

# Scripts
### **Description**
Shell script description and usage (use markdown code block for script usage)
- psql_docker.sh ...
- host_info.sh ...
- host_usage.sh ...
- crontab ...
- queries.sql (describe what business problem you are trying to resolve)

### **Usage**

- [psql_docker.sh](./README.md) :
 ```
 git status
 git add
 git commit
```
 
- [host_info.sh](./README.md) :
 ```
 git status
 git add
 git commit
```
 
- [host_usage.sh](./README.md) :
 ```
 git status
 git add
 git commit
```

- [dummy_data.sh](./README.md) :
 ```
 git status
 git add
 git commit
```

- [queries.sql](./README.md) :
 ```
 git status
 git add
 git commit
```

- [ddl.sql](./README.md) :
 ```
 git status
 git add
 git commit
```
 
# Deployment
How did you deploy your app? (e.g. Github, crontab, docker)

# Improvements
1. handle hardware update 
2. ...
3. ...
