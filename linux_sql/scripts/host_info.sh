#!/bin/bash

# Get the CLI arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#Check the number of args
if [ $# -ne  5 ]; then
  echo 'non valid arguments'
  echo 'usage: ./scripts/host_info.sh [psql_host] [psql_port] [db_name] [psql_user] [psql_password]'
  echo 'e.g. ./scripts/host_info.sh localhost 5432 host_agent postgres password'
  exit 1
fi


lscpu_out=`lscpu`

hostname=$(hostname -f)

cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)

cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)

STR=$(echo "$lscpu_out"  | egrep "^Model name:" | awk '{print}' | xargs)
cpu_model=${STR:12:50}

STR=$(echo "$lscpu_out"  | egrep "^CPU MHz:" | awk '{print}' | xargs)
cpu_mhz=${STR:9:50}

STR=$(echo "$lscpu_out"  | egrep "^L2 cache:" | awk '{print}' | xargs)
l2_cache=${STR:10:50}
l2_cache=${l2_cache::-1}

STR=$(cat /proc/meminfo  | egrep "^MemTotal:" | awk '{print}' | xargs)
total_mem=${STR:10:50}
total_mem=${total_mem::-3}

timestamp=$(date +%F_%T)

STR=$(cat /proc/meminfo  | egrep "^MemFree:" | xargs)
memory_free=${STR:9:50}
memory_free=${memory_free::-3}

STR=$(top -n 1 -b  | egrep "^%Cpu\(s\)" | xargs)
cpu_idle=$(echo ${STR:8:50} | awk -F, '{print $4}' | xargs)
cpu_idle=${cpu_idle::-3}


str=$(vmstat --unit M -D | tail -n 2)
disk_io=(echo $str)

echo "host name is $hostname"
echo "psql_host is $psql_host"
echo "psql_port is $psql_port"
echo "db_name is $db_name"
echo "psql_user is $psql_user"
echo "psql_password is $psql_password"

echo "cpu_number is $cpu_number"
echo "cpu_architecture is $cpu_architecture"
echo "cpu_model is $cpu_model"
echo "cpu_mhz is $cpu_mhz"
echo "l2_cache is $l2_cache"
echo "total_mem is $total_mem"
echo "timestamp is $timestamp"
echo "memory_free is $memory_free"
echo "cpu_idle is $cpu_idle"


# construct the INSERT statement
insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, timestamp)
VALUES ('$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$l2_cache', '$total_mem', '$timestamp');"

export PGPASSWORD=$psql_password

# execute the INSERT statement through psql CLI tool
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit 0;
