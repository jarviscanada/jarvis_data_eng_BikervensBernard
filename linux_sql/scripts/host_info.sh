#!/bin/bash

# assign CLI arguments to variables (e.g. `psql_host=$1`)
# parse host hardware specifications using bash cmds
hostname=$1
echo "hostname is $hostname"

lscpu_out=`lscpu`

cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
echo "cpu_number is $cpu_number"

cpu_architecture=$(echo "$cpu"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
echo "cpu_architecture is $cpu_architecture"

STR=$(echo "$cpu"  | egrep "^Model name:" | awk '{print}' | xargs)
cpu_model=${STR:12:50}
echo "cpu_model is $cpu_model"

STR=$(echo "$cpu"  | egrep "^CPU MHz:" | awk '{print}' | xargs)
cpu_mhz=${STR:9:50}
echo "cpu_mhz is $cpu_mhz"

STR=$(echo "$cpu"  | egrep "^L2 cache:" | awk '{print}' | xargs)
l2_cache=${STR:10:50}
l2_cache=${l2_cache::-1}
echo "l2_cache is $l2_cache"

STR=$(cat /proc/meminfo  | egrep "^MemTotal:" | awk '{print}' | xargs)
total_mem=${STR:10:50}
total_mem=${total_mem::-3}
echo "total_mem is $total_mem"

timestamp=$(date +%F_%T)
echo "timestamp is $timestamp"

STR=$(cat /proc/meminfo  | egrep "^MemFree:" | xargs)
memory_free=${STR:9:50}
memory_free=${memory_free::-3}
echo "memory_free is $memory_free"

STR=$(top -n 1 -b  | egrep "^%Cpu\(s\)" | xargs)
cpu_idle=$(echo ${STR:8:50} | awk -F, '{print $4}' | xargs)
cpu_idle=${cpu_idle::-3}
echo "cpu_idle is $cpu_idle"


# construct the INSERT statement
# execute the INSERT statement through psql CLI tool
