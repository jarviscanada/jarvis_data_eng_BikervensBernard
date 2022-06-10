# **Introduction**

This project implements a Java version of the Linux grep command, which allows users to search for matching strings from files. This app recursively traverses a given file path and will output all matching strings of a given regex into a given output file. The user can the cat the output file to see the matching strings. The project utilizes the java Stream API for efficientcy, Maven compiler for denpendencies management, and Docker for the distribution.


# Quick Start
```
# in shell
bash cd core_java/grep
bash mvn clean compile package 
java -cp target/grep-1.0-SNAPSHOT.jar [MAIN_CLASS_PATH] [REGEX] [SEARCHING_PATH] [OUTPUT_FILE_PATH]

#for large file large:
java -Xms[MIN_HEAP_SIZE] -Xmx[MAX_HEAP_SIZE] -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrep [REGEX] [ROOT_PATH] [OUTPUT_FILE]
```

| Argument           | Description                              | Example                    |
|--------------------|------------------------------------------|----------------------------|
| `[MAIN_CLASS_PATH]`| path of main class                       | ca.jrvs.apps.grep.JavaGrep |
| `[REGEX]`          | A Java Regex to match with.              | .*Romeo.*Juliet.* |
| `[SEARCHING_PATH]` | The root file path to search.            | ./src/main/resources/data |
| `[OUTPUT_FILE_PATH]`| The file to write the search results to.| ./src/main/resources/out/out.txt |
| `[MIN_HEAP_SIZE]`   | The minimum heap size to use.           | -Xms5m |
| `[MAX_HEAP_SIZE]`   | The maximum heap size to use.           | -Xmx5m |


# **Design**

As a junior developer, you will develop two Java applications:

- A Java app that mimics Linux `grep` command which allows users to search matching strings from files.
- A Java application that read, write, update, and deletes (CURD) data against an RDBMS database using JDBC

# Implementation
## Pseudocode
write `process` method pseudocode.

You will need to implements three Java applications using the following technologies:

- Core Java Features

  ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2e024854-6721-41e2-8a76-10824ecd36a0/Java-Topics.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2e024854-6721-41e2-8a76-10824ecd36a0/Java-Topics.png)

- Java Libraries and Tools
    - Maven
    - JDBC
    - JSON libraries
    - HTTP clients
    - JUnit & Mockito
- Design Principles
    - DRY
    - KISS

## Performance Issue
(30-60 words)
Discuss the memory issue and how would you fix it

# Test

- Manual testing
- IDE debugger
- JUnit (if required)

# Deployment/Delivery
- Distribute app using Docker images
How you dockerize your app for easier distribution?

# Improvement
List three things you can improve in this project.
