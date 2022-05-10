#Setup arguments
cmd=$1
db_username=$2
db_password=$3

#validate argumnents
if [[ "$#" -lt 1 ]]; then
	echo "Illegal number of parameters"
	exit 1
else 
	if [[ "$#" -gt 3 ]]; then
		echo "Illegal number of parameters"
		exit 1
	fi
fi

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
container_status=$(docker ps -a | grep -c "$db_username")
if [ $container_status -eq 1 ]; then
	id=$(docker ps -a -qf "name=$db_username")
	if [ $cmd = "start" ]; then
		docker container start "$id"
		echo "container $db_username started"
		exit 0
	fi

	if [ $cmd = "stop" ]; then
		docker container stop "$id"
		echo "container $db_username was stopped"
		exit 0
	fi

	if [ $cmd = "delete" ]; then
		docker container stop "$id"
		sleep 3
		docker container rm "$id"
		echo "container $db_username was removed"
		exit 0
	fi
else 
	if [ $container_status -eq 1 ]; then
		echo "container already exist"
		exit 0
	fi
	if (( $cmd == "create" )); then
		#check # of CLI arguments
		if [[ $# -ne 3 ]]; then
			echo "$cmd requires username and password"
			exit 1;
		else
			#create container
			echo "Container $db_username does NOT exists. Let's create it..."
			echo "Creating container..."
			
			#get latest postgres image
			docker pull postgres
			
			#create a new volume if not exist
			docker volume create pgdata

			#set password for default user `postgres`
			docker run --name $db_username -e POSTGRES_USER=$db_username -e POSTGRES_PASSWORD=$db_password -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine
			sleep 2

			#start/stop a container
			container_status=$(docker ps -a | grep -c "$db_username")
			if [ $container_status -eq 1 ]; then
				echo "Container $db_username created"
				docker container start "$db_username"
			fi 
		fi
	else 
		echo 'Illegal command'
		echo 'Allowed Commands: create|start|stop|delete [user] [password]'
		exit 1
	fi
fi
exit 0