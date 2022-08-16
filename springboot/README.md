Table of contents
* [Introduction](#Introduction)
* [Quick Start](#QuickStart)
* [Implemenation](#Implemenation)
* [Test](#Test)
* [Deployment](#Deployment)
* [Improvements](#Improvements)

# Introduction
A legacy system, a monolithic application that is challenging to grow and operate, is being replaced by this application. I created a new trading system using the microservice architecture and Springboot framework as a member of the backend team. The architecture of the project makes it easier to scale and manage since the idea is to split your application into a set of smaller, interconnected services instead of building a single monolithic application. Each microservice is a small application that has its own hexagonal architecture consisting of business logic along with various adapters. 

## What does your project does?
I don't have to deal with the actual banking or exchange systems because this is just a proof of concept. More initiative will be done if the PoC is a success. Instead of emphasising performance and security, the PoC project concentrates on features (such as functions and feasibility). The application is a REST API consumed by the front-end applications (e.g. web or mobile apps). The application allows users to manage client profiles and accounts, monitor portfolio performance, and trade securities. 

## Technologies used
The REST API, which manages fundamental business logic including managing trader profiles and carrying out security orders, was implemented using Java 8 and Springboot. All information must be saved in a PostgreSQL database because the Springboot application is stateless. The Springboot software retrieves information from IEX Cloud via its REST API, which provides free market data (such as stock price). 

# Quick Start
- Prequiresites: Docker 17.05 or higher, CentOS 7

## Create a docker network
```
#downalod images
docker pull jrvs/trading-app-demo:latest
docker pull jrvs/trading-psql-demo:latest

#create a new docker network
docker network create --driver bridge trading-net

#Register IEX Cloud free account and obtain credentials https://iexcloud.io/console/tokens
#Set IEX credential 
IEX_PUB_TOKEN="pk_XXXXXXXXXXXXXXXX"
```

## Start containers
```
#start the postgres container
#attached the container to the trading-net network
docker run --rm --name trading-psql-demo-local -e POSTGRES_PASSWORD=password -e POSTGRES_DB=jrvstrading -e POSTGRES_USER=postgres --network trading-net -d -p 5432:5432 jrvs/trading-psql-demo

#start trading-app container which is attached to the trading-net docker network
docker run -d --rm --name trading-app-demo-local -e "PSQL_HOST=trading-psql-demo-local" -e "PSQL_PORT=5432" -e "PSQL_USER=postgres" -e "PSQL_DB=jrvstrading" -e "PSQL_PASSWORD=password" -e "IEX_PUB_TOKEN=${IEX_PUB_TOKEN}" --network trading-net -p 5000:5000 -t jrvs/trading-app-demo

#Verify two running docker containers
docker ps
```

## Expected result
|ID|IMAGE|COMMAND|CREATED|STATUS|PORTS|NAMES|
|---|---|---|---|---|---|---|
|c590b8e19401|jrvs/trading|"java -jar /usr/loca…"|4 seconds ago|Up 3 seconds|0.0.0.0:5000->5000/tcp|trading-app-demo-local|
|5cf54bfcc928|jrvs/trading-psql-demo|"docker-entrypoint.s…"|About a minute ago|Up About a minute|0.0.0.0:5432->5432/tcp|trading-psql-demo-local|

## Try trading-app with SwaggerUI
```
#Try REST APIs with your browser
http://localhost:5000/swagger-ui.html

#stop containers
docker container stop trading-app-demo-local trading-psql-demo-local
```

# Implemenation
## Architecture
![assets\spring.png](https://github.com/jarviscanada/jarvis_data_eng_BikervensBernard/blob/release/springboot/assets/spring.png?raw=true)

- `The Controller`: The conductor of operations for a request. It controls the transaction scope and manages the session related information for the request. The controller first dispatches to a command and then calls the appropriate view processing logic to render the response
  - `Service layer`: Mediates communication between a controller and repository layer. The service layer contains business logic. In particular, it contains validation logic.
  - `DAO layer`: Provides an abstract interface to some type of database or other persistence mechanisms. By mapping application calls to the persistence layer, DAOs provide some specific data operations without exposing details of the database.
  - `SpringBoot`: webservlet/TomCat and IoC SpringBoot is used for IoC (Inversion of Control) dependency injection. Furthermore, it creates and manages the WebServlet container/Apache Tomcat, which hosts the webpage for this app.
  - `PSQL` and `IEX`: PostgreSQL is used for all data persistence on this project. IEX Cloud provides the REST API for all their market data (namely to generate quotes).

## REST API Usage
### Swagger
wagger UI allows anyone — be it your development team or your end consumers — to visualize and interact with the API’s resources without having any of the implementation logic in place. It’s automatically generated from your OpenAPI (formerly known as Swagger) Specification, with the visual documentation making it easy for back end implementation and client side consumption.

### Quote Controller
manages market data read from IEX Cloud’s REST API, persists data to PostgreSQL external database.
- Endpoints:
  - GET `/quote/iex/ticker/{ticker}`: get a iex quote for the specified ticker.
  - GET `/quote/iex/MarketData`: Update quote table using IEX trading API as market live data source
  - GET `/quote/dailylist/{tickers}`: Add in backend the specified daily list quote(s). Fetch iex data fo specified quotes, save data to table and return list

### Trader Controller
- Endpoints:
  - GET `/trader/traders`: Fetch all traders in database and returns
  - GET `/trader/traders/accounts`: Fetch all accounts in database an returns
  - GET `trader/firstname/{firstname}/lastname/{lastname}/dob/{dob}/country/{country}/email/{email}/gender/{gender}`: Create trader and an account to the database
  - GET `trader/firstname/{firstname}/lastname/{lastname}/dob/{dob}/country/{country}/email/{email}/gender/{gender}`: Create trader and an account to the database
  - PUT `trader/update/id/{id}/firstname/{firstname}/lastname/{lastname}/country/{country}/email/{email}/gender/{gender}`: Update trader's personal information to the database
  - DELETE `trader/delete/traderid/{traderId}`: Delete a trader if its account amount is 0 and no open positions
  - GET `trader/deposit/trader/{traderId}/amount/{amount}`: Add fund to the trader account
  - GET `trader/withdraw/trader/{traderId}/amount/{amount}`: Withdraw fund to the trader account

# Test 
JUnit4 was used to do automated integration testing. To guarantee that production and development were kept separate, this testing was carried out with an above 60% code line coverage in a separate database testing environment. Postman and SwaggerUI were also used for manual testing to test endpoints.

# Deployment
- docker diagram including images, containers, network, and docker hub
![dockerimage](https://github.com/jarviscanada/jarvis_data_eng_BikervensBernard/blob/release/springboot/assets/docker%20image.png?raw=true)
- Description:
	- `Docker CLI`: the commands used in the CLI to build/run/create/etc. the components in `Docker Host`. See `Quick Start`(#Quick-Start) for more information.
	- `Docker Host`:
		- `Docker daemon`: listens for Docker API requests and manages Docker objects such as images, containers, networks, and volumes.
		- `Images`: the trading-psql (database image) and trading-app (application image) that provide the volume for the container's instantiation.
		- `Containers`: the trading-psql-dev container for the database, and the trading-app-dev container for the trading application.
		- `Networks`: the trading-net network which both containers use to communicate with one another over port 5432.
	- `Docker Hub`: where both images are pushed to (both the database, and the Java application).


# Improvements
- 1: `Order Controller`: Implement an `Order Controller` that could execute market orders, buy orders, and sell orders.
- 2: `DashBoard Controller`: Implement an `Order Controller` to provide a detailed way to view trader, account, and portfolio information.
- 3: more robust code testing and other test case.
