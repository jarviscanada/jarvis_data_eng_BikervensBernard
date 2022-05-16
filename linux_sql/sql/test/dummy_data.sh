#!/bin/bash

# Get the CLI arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

export PGPASSWORD=$psql_password

# Save machine information to variables for later
meminfo=$(cat /proc/meminfo)
vmstat_t=$(vmstat -t --unit M)
lscpu_out=`lscpu`
UUID=$(cat /proc/sys/kernel/random/uuid |  awk -F "-" '{print $1}')
hostname=$(echo "jrd $UUID")
cpu_number=2
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "^Model name:" | awk -F ":" '{print $2}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out"  | egrep "^L2 cache:" | awk '{print $3}' | xargs | cut -d'K' -f 1)
total_mem=1000
# Current time in `2019-11-26 14:40:19` UTC format
timestamp=$(echo "${vmstat_t}"  | awk '{print $18, $19}' | xargs | awk '{print $2, $3}')
	
function set_data  {
	UUID=$(cat /proc/sys/kernel/random/uuid |  awk -F "-" '{print $1}')
	$hostname=$(echo "jrd $UUID")
	$cpu_number=$1
	$cpu_architecture=$2
	$cpu_model=$3
	$cpu_mhz=$4
	$l2_cache=$5
	$total_mem=$6
	# Current time in `2019-11-26 14:40:19` UTC format
	$timestamp=$(echo "${vmstat_t}"  | awk '{print $18, $19}' | xargs | awk '{print $2, $3}')
}


## 1-- Get the specific hardware specification variables node1 --
set_data 2 $cpu_architecture $cpu_model $cpu_mhz $l2_cache $total_mem

# Construct the INSERT statement
insert_stmt= "INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, \"timestamp\")
VALUES ($hostname, $cpu_number, '$cpu_architecture', '$cpu_model', $cpu_mhz, $l2_cache, $total_mem, '$timestamp');"

# Execute the INSERT statement through psql CLI tool
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit 0
