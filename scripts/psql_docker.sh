#setup arguments
cmd=$1
db_username=$2
db_password=$3

#validate argumnents
if [[ "$#" -lt 1 ]]; then
	echo "Illegal number of parameters"
	exit 1
else 
	if [[ "$#" -eq 2 ]]; then
		echo "Illegal number of parameters"
		exit 1
	else 
		if [[ "$#" -gt 3 ]]; then
			echo "Illegal number of parameters"
			exit 1
		fi
	fi 
fi

#parse hardware specification
hostname=$(hostname -f)
lscpu_out=`lscpu`
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)

#start docker
status=$(systemctl show --property ActiveState docker | xargs  | awk -F'=' '{print $2}')
if (( $status == "active" )); then
 	echo "docker deamon is active"
else
 	echo "docker deamon is being activated..."
	systemctl start docker
	sleep 5
	status=$(systemctl show --property ActiveState docker | xargs  | awk -F'=' '{print $2}')
	echo "docker deamon is now active"
fi

# Check if the container is already created
container_status=$(docker ps -a | grep -c "jrvs-psql")
if [ $container_status -eq 1 ]; then
	echo "Container jrvs-psql already exists"
	id=$(docker ps -a -qf "name=jrvs-psql")
	echo "Container jrvs-psql id is $id"
	if [ "$cmd" = "start" ]; then
		docker container start "$id"
		exit 2
	fi

	if [ "$cmd" = "stop" ]; then
		docker container stop "$id"
		echo 'container jrvs-psql was stopped'
		exit 3
	fi

	if [ "$cmd" = "delete" ]; then
		docker container stop "$id"
		sleep 3
		docker container rm "$id"
		echo 'container jrvs-psql was removed'
		exit 4
	fi
else 
	echo "Container jrvs-psql does NOT exists. Let's create it..."
	#check # of CLI arguments
	if (( $cmd == "create" )); then
		if [[ $# -ne 3 ]]; then
			echo "Create requires username and password"
			exit 5;
		else
			#Create container
			echo "Creating container..."
			docker pull postgres
			docker volume create pgdata
			export PGPASSWORD=$db_password
			docker run --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine
			sleep 5
			container_status=$(docker ps -a | grep -c "jrvs-psql")
			if [ $container_status -eq 1 ]; then
				echo "Container jrvs-psql created"
				docker container start jrvs-psql

			fi 
		fi
	else 
		echo 'Illegal command'
		echo 'Commands: start|stop|create|delete'
		exit 6
	fi
fi
exit 0


