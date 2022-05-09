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
container_status=$(docker ps -a | grep -c "$db_username")
if [ $container_status -eq 1 ]; then
	echo "Container $db_username already exists"
	id=$(docker ps -a -qf "name=$db_username")
	echo "Container "$db_username" id is $id"
	if [ $cmd = "start" ]; then
		docker container start "$id"
		exit 2
	fi

	if [ $cmd = "stop" ]; then
		docker container stop "$id"
		echo "container $db_username was stopped"
		exit 3
	fi

	if [ $cmd = "delete" ]; then
		docker container stop "$id"
		sleep 3
		docker container rm "$id"
		echo "container $db_username was removed"
		exit 4
	fi
else 

	#check # of CLI arguments
	if (( $cmd == "create" )); then
		if [[ $# -ne 3 ]]; then
			echo "$cmd requires username and password"
			exit 5;
		else
			#Create container
			echo "Container $db_username does NOT exists. Let's create it..."
			echo "Creating container..."
			docker pull postgres
			docker volume create pgdata
			docker run --name $db_username -e POSTGRES_USERNAME -e POSTGRES_PASSWORD --env POSTGRES_USERNAME=$db_username --env POSTGRES_PASSWORD=$db_password -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine
			sleep 5
			container_status=$(docker ps -a | grep -c "$db_username")
			if [ $container_status -eq 1 ]; then
				echo "Container $db_username created"
				docker container start "$db_username"
			fi 
		fi
	else 
		echo 'Illegal command'
		echo 'Allowed Commands: create|start|stop|delete [user] [password]'
		exit 6
	fi
fi
exit 0