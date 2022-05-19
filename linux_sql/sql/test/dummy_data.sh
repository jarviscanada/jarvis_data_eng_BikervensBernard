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

# Get the specific hardware specification variables node1
UUID=$(cat /proc/sys/kernel/random/uuid |  awk -F "-" '{print $1}')
hostname=$(echo "jrd $UUID")
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "^Model name:" | awk -F ":" '{print $2}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out"  | egrep "^L2 cache:" | awk '{print $3}' | xargs | cut -d'K' -f 1)
total_mem=1000
# Current time in `2019-11-26 14:40:19` UTC format
timestamp=$(echo "${vmstat_t}"  | awk '{print $18, $19}' | xargs | awk '{print $2, $3}')

# Construct the INSERT statement
insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, \"timestamp\")
VALUES ('$hostname', $cpu_number, '$cpu_architecture', '$cpu_model', $cpu_mhz, $l2_cache, $total_mem, '$timestamp');"

# Validate dummy node
echo "node 1 hostname is $hostname"
echo "node 1 cpu_number is $cpu_number"
echo "node 1 cpu_architecture is $cpu_architecture"
echo "node 1 cpu_model is $cpu_model"
echo "node 1 cpu_mhz is $cpu_mhz"
echo "node 1 l2_cache is $l2_cache"
echo "node 1 total_mem is $total_mem"
echo ""
echo ""

# Execute the INSERT statement through psql CLI tool
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"


# Get the specific hardware specification variables node2
UUID=$(cat /proc/sys/kernel/random/uuid |  awk -F "-" '{print $1}')
hostname=$(echo "jrd $UUID")
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "^Model name:" | awk -F ":" '{print $2}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out"  | egrep "^L2 cache:" | awk '{print $3}' | xargs | cut -d'K' -f 1)
total_mem=100
# Current time in `2019-11-26 14:40:19` UTC format
timestamp=$(echo "${vmstat_t}"  | awk '{print $18, $19}' | xargs | awk '{print $2, $3}')

# Validate dummy node
echo "node 2 hostname is $hostname"
echo "node 2 cpu_number is $cpu_number"
echo "node 2 cpu_architecture is $cpu_architecture"
echo "node 2 cpu_model is $cpu_model"
echo "node 2 cpu_mhz is $cpu_mhz"
echo "node 2 l2_cache is $l2_cache"
echo "node 2 total_mem is $total_mem"
echo ""
echo ""

# Construct the INSERT statement
insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, \"timestamp\")
VALUES ('$hostname', $cpu_number, '$cpu_architecture', '$cpu_model', $cpu_mhz, $l2_cache, $total_mem, '$timestamp');"

# Execute the INSERT statement through psql CLI tool
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"










# Get the specific hardware specification variables node3
UUID=$(cat /proc/sys/kernel/random/uuid |  awk -F "-" '{print $1}')
hostname=$(echo "jrd $UUID")
cpu_number=4
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "^Model name:" | awk -F ":" '{print $2}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=233
total_mem=1002
# Current time in `2019-11-26 14:40:19` UTC format
timestamp=$(echo "${vmstat_t}"  | awk '{print $18, $19}' | xargs | awk '{print $2, $3}')

# Validate dummy node
echo "node 3 hostname is $hostname"
echo "node 3 cpu_number is $cpu_number"
echo "node 3 cpu_architecture is $cpu_architecture"
echo "node 3 cpu_model is $cpu_model"
echo "node 3 cpu_mhz is $cpu_mhz"
echo "node 3 l2_cache is $l2_cache"
echo "node 3 total_mem is $total_mem"
echo ""
echo ""

# Construct the INSERT statement
insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, \"timestamp\")
VALUES ('$hostname', $cpu_number, '$cpu_architecture', '$cpu_model', $cpu_mhz, $l2_cache, $total_mem, '$timestamp');"

# Execute the INSERT statement through psql CLI tool
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"









# Get the specific hardware specification variables node4
UUID=$(cat /proc/sys/kernel/random/uuid |  awk -F "-" '{print $1}')
hostname=$(echo "jrd $UUID")
cpu_number=4
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "^Model name:" | awk -F ":" '{print $2}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=283
total_mem=1302
# Current time in `2019-11-26 14:40:19` UTC format
timestamp=$(echo "${vmstat_t}"  | awk '{print $18, $19}' | xargs | awk '{print $2, $3}')

# Validate dummy node
echo "node 4 hostname is $hostname"
echo "node 4 cpu_number is $cpu_number"
echo "node 4 cpu_architecture is $cpu_architecture"
echo "node 4 cpu_model is $cpu_model"
echo "node 4 cpu_mhz is $cpu_mhz"
echo "node 4 l2_cache is $l2_cache"
echo "node 4 total_mem is $total_mem"
echo ""
echo ""

# Construct the INSERT statement
insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, \"timestamp\")
VALUES ('$hostname', $cpu_number, '$cpu_architecture', '$cpu_model', $cpu_mhz, $l2_cache, $total_mem, '$timestamp');"

# Execute the INSERT statement through psql CLI tool
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit 0
