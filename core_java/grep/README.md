# **Introduction**

This project implements a Java version of the Linux grep command, which allows users to search for matching strings from files. This app recursively traverses a given file path and will output all matching strings of a given regex into a given output file. The user can the cat the output file to see the matching strings. The project utilizes the java Stream API for efficientcy, Maven compiler for denpendencies management, and Docker for the distribution.


# Quick Start
```
# in shell
bash cd core_java/grep
bash mvn clean compile package 
java -cp target/grep-1.0-SNAPSHOT-UBER.jar [MAIN_CLASS_PATH] [REGEX] [SEARCHING_PATH] [OUTPUT_FILE_PATH]

#for large file large:
java -Xms[MIN_HEAP_SIZE] -Xmx[MAX_HEAP_SIZE] -cp target/grep-1.0-SNAPSHOT-UBER.jar ca.jrvs.apps.grep.JavaGrep [REGEX] [SEARCHING_PATH] [OUTPUT_FILE_PATH]
```

| Argument           | Description                              | Example                    |
|--------------------|------------------------------------------|----------------------------|
| `[MAIN_CLASS_PATH]`| path of main class                       | ca.jrvs.apps.grep.JavaGrep |
| `[REGEX]`          | A Java Regex to match with.              | .*Romeo.*Juliet.* |
| `[SEARCHING_PATH]` | The root file path to search.            | ./src/main/resources/data |
| `[OUTPUT_FILE_PATH]`| The file to write the search results to.| ./src/main/resources/out/out.txt |
| `[MIN_HEAP_SIZE]`   | The minimum heap size to use.           | -Xms5m |
| `[MAX_HEAP_SIZE]`   | The maximum heap size to use.           | -Xmx5m |

# Implementation
## Pseudocode for process():
```
matchedLines = []
for file in listFiles(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToOutFile(matchedLines)
```

## Performance Issue
There is a notable issue when the data to be read is larger than the allocated memory size. In that scenario, the app will crash with an OutOfMemoryError. To fix this issue, we use java's "Stream APIs". Simply put, streams are wrappers over a data source that allow us to interact with it while also making bulk processing simple and quick. We can change the grep interface to return streams instead of lists. The java stream allows me to keep a small heap memory while being able to process large files. With this change, we can support larger file sizes without the threat of memory errors.

# Test
Testing was done manuallay. 
Here is an example of manual testing using linux grep command against my java grep implementation

Expected:
```bash
$ grep .*Romeo.*Juliet.* ./data/txt/shakespeare.txt 
    Is father, mother, Tybalt, Romeo, Juliet,
Enter Romeo and Juliet aloft, at the Window.
    And Romeo dead; and Juliet, dead before,
    Romeo, there dead, was husband to that Juliet;

# My implementation 
$ java -cp target/grep-1.0-SNAPSHOT-UBER.jar ca.jrvs.apps.grep.JavaGrepImp .*Romeo.*Juliet.* ./data ./out/grep.out
```
Same output should occur.


# Deployment/Delivery
- Distribute app using Docker images which can be pulled from my repository. Here are the step of the docker image creation:
```bash
docker_user=your_docker_id
docker login -u ${docker_user}

#Create the dockerfile 
cat > Dockerfile << EOF
FROM openjdk:8-alpine
COPY target/grep*.jar /usr/local/app/grep/lib/grep.jar
ENTRYPOINT ["java","-jar","/usr/local/app/grep/lib/grep.jar"]
EOF

#Package the java app
mvn clean package

#build the new docker image locally
docker build -t ${docker_user}/grep .

#run the docker container 
docker run --rm \
-v `pwd`/data:/data -v `pwd`/log:/log \
${docker_user}/grep .*Romeo.*Juliet.* /data /log/grep.out

#push the image to Docker Hub
docker push ${docker_user}/grep
```
You can manually create a new docker image by following the step of you could use the already existing docker image: `docker push bernard76/grep`
```bash
 # container run result will be available at: 
 cat '[PATH_TO_DESKTOP]/jarvis_data_eng_BikervensBernard/core_java/grep/target/classes/out/out.txt'
```
# Improvement
1. Additional memory optimization to improve memory consumption
2. Additional testing - as of now the application was tested using manually given a file and expected output. it would be preferred to use junit or something similar to fully test the application.
3. Add extra error handling to ensure that the app continues to work even if the arguments are incorrect.
4. Add extra error handling to ensure that the app continues to work even if the directory is empty or if the files are not readable
