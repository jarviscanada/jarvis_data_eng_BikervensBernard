
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
- Create a psql instance using `psql_docker.sh`: bash ./scripts/psql_docker.sh create [database's username] [database's password] 
```
bash ./scripts/psql_docker.sh create postgres password
```

<br/>

- Start a psql instance using `psql_docker.sh`: By default the container's name is `postgres`
```
bash ./scripts/psql_docker.sh start
```

<br/>

- Stop a psql instance using `psql_docker.sh` : The database will be persisted
```
bash ./scripts/psql_docker.sh stop
```

<br/>

- Create tables using `ddl.sql` (this is ran during the container's creation. Therefore the create cmd on `psql_docker.sh` will have the same effect): <br/> psql -h [postgres host] -U [postgres username] -d [database] -f [path to ddl.sql]
```
psql -h localhost -U postgres -d host_agent -f ./sql/ddl.sql
```

<br/>

- Using `host_info.sh` run ./scripts/host_usage.sh [postgres host] [postgres port] [database] [postgres username] [postgres password] once on the monitoring agent to insert hardware specs data into the DB
```
./scripts/host_usage.sh localhost 5432 host_agent posgress password
```

<br/>

- Insert each minute the hardware memory and cpu usage data into the DB using `host_usage.sh` <br/> ./scripts/host_usage.sh [host] [psql_port] [database] [database username] [database password]
```
./scripts/host_usage.sh localhost 5432 host_agent postgres password
```

<br/>

- Crontab setup to automate the host_usage.sh script's updates using Crontab. Here is how to set this up:
```
#edit crontab jobs
bash> crontab -e

#add this to crontab (always full path)
## vi / vim always begins in command mode. You can press [Esc] key anytime to return to command mode. Press i to insert text. To save ## and exit from vi / vim, press [Esc] key and type ZZ
* * * * * bash /home/centos/Desktop/jarvis_data_eng_BikervensBernard/linux_sql/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log

#list crontab jobs
crontab -l

#validate your result from the psql instance
psql -h ...
> SELECT * FROM host_usage;
```

# Scripts
### **Description**
Shell script description and usage (use markdown code block for script usage)
- `psql_docker.sh` provision a psql instance using docker. Stop, start, create and delete said docker's container
- `host_info.sh` collects hardware specification data and then insert the data to the psql instance. You can assume that hardware specifications are static, so the script will be executed only once. 
- `host_usage.sh` collects node's usage data and then insert the data into the psql database. The script will be executed every minute using Linux crontab 
- `crontab` insert a new entry to the host_usage table every minute when the server is healthy. We can assume that a server is failed when it inserts less than three data points within 5-min interval
- `queries.sql` (describe what business problem you are trying to resolve)

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
