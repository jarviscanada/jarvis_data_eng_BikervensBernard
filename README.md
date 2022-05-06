
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

![https://miro.medium.com/max/1052/1*mqDOdPn-r8OQgR1UyLRXkA.png](https://miro.medium.com/max/1052/1*mqDOdPn-r8OQgR1UyLRXkA.png)

- A `psql` instance is used to persist all the data
- The `bash agent` gathers server usage data, and then insert into the psql instance. The `agent` will be installed on every 

host/server/node. The `agent` consists of two bash scripts
    - `host_info.sh` collects the host hardware info and insert it into the database. It will be run only once at the installation time.
    - `host_usage.sh` collects the current host usage (CPU and Memory) and then insert into the database. It will be triggered by the `crontab` job every minute.

Each nodes are on a same network connected.
- A `psql` data base is used to save the data
- The `node's` agent gathers server usage data, and then insert into the psql instance. The `agent` is on every node. 

The `agent` consists of two bash scripts
- `host_info.sh` collects the host hardware info and insert it into the database. It will be run only once at the installation time.
- `host_usage.sh` collects the current host usage (CPU and Memory) and then insert into the database. It will be triggered by the `crontab` job every minute.

### **Database**
- `host_info`
    - `host_id` : The unique identifier
    - `host_name` : The full name of the node
    - `host_cpu_number` : The sum of cpu core
    - `host_cpu_architecture` : The generation 
    - `host_cpu_model` : The manifacturer
    - `host_cpu_mhz` : The sum, in megahertz, of the actively used CPU
    - `host_L2_cache` : The sum, in mb memory bank built into the CPU
    - `host_tot_memory` : The amount of ram
    - `host_timestamp` : UTC timestamp (epoch)
- `host_usage`
    - `host_id` : unique identifier
    - `host_timestamp` : UTC timestamp (epoch)
    - `host_available_memo` : The amount of memory not being used
    - `host_cpu_idel` :
    - `host_cpu_kernel` :
    - `host_disk_io` : The amount of disk space used
    - `host_disk_available` : The amount of disk space available

# Test

tested on a single machine instead of a Linux cluster. 
However, should work in a cluster environment, assuming connection and firewalls are set up correctly (done by other teams). For bash scripts, you should test/verify all functionalities manually. You will need to verify the query result against test data (created by developers) for SQL scripts.

# Deployment/DevOps

The agent program is scheduled using `crontab`. Source code is managed by GitHub. Database is provisioned with Docker.

# Quick Start
Use markdown code block for your quick-start commands
- Start a psql instance using psql_docker.sh
- Create tables using ddl.sql
- Insert hardware specs data into the DB using host_info.sh
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
 
- [queries.sql](./README.md) :
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
