Table of contents
* [Introduction](#Introduction)
* [Quick Start](#Quick-Start)
* [Implementation](#Implementation)
* [Test](#Test)
* [Deployment](#Deployment)
* [Improvements](#Improvements)

# Introduction
A new trading system with the microservice architecture and Springboot 
framework to implement the REST API which handles core business logic.
Since this is a PoC (proof of concept),
the focus is on features (e.g. functionalities, and feasibility) 
rather than performance and security. The trading platform MVP is a 
REST API consumed by the front-end applications (e.g. web or mobile apps). 
The application allows users to manage client profiles and accounts, 
monitor portfolio performance, and trade securities using standard libraries 
like JdbcTemplate, Springboot framework, 
Swagger (created the UI), PostgreSQL (the backend database), 
Maven, and Docker.

# Quick-Start
## How to tun the app using Maven
```bash
git clone https://github.com/jarviscanada/jarvis_data_eng_BikervensBernard.git
cd ./jarviscanada/jarvis_data_eng_BikervensBernard/core_java/springboot

#compile and package your Java code
mvn clean compile package 
mvn spring-boot:run 

#Go to :
http://localhost:8080/swagger-ui.html#/
```

## How to run the app using Docker
```bash
#To pull my Docker image
docker pull bernard76/trading-app:latest 
docker pull bernard76/trading-psql:latest 
#To run my Docker image
docker run --name trading-psql-dev \ 
-e POSTGRES_PASSWORD=password \
-e POSTGRES_USER=postgres \ 
--network trading-net \ 
-d -p 5432:5432 trading-psql

# There should be one running containers
docker ps

docker run --name trading-app-dev \ 
-e "PSQL_URL=jdbc:postgresql://trading-psql-dev:5432/jrvstrading" \ 
-e "PSQL_USER=postgres" \ 
-e "PSQL_PASSWORD=password" \ 
--network trading-net -p 8080:8080 -t trading-app


#There should be two running containers
docker container ls
#Go to :
http://localhost:8080/swagger-ui.html#/
```

# Implementation
## Architecture
- Draw a component diagram that contains controllers, services, DAOs, SQL, IEX Cloud, WebServlet/Tomcat, HTTP client, and SpringBoot. (you must create your own diagram)
- briefly explain the following components and services (3-5 sentences for each)
  - Controller layer (e.g. handles user requests....)
  - Service layer
  - DAO layer
  - SpringBoot: webservlet/TomCat and IoC
  - PSQL and IEX

## REST API Usage
### Swagger
Swagger is used to describe the structure of your 
APIs so that machines can read them.
Why is it so great? Well, by reading your API’s structure, 
we can automatically build beautiful and interactive API documentation.
Swagger does this by asking your API to return a YAML or JSON that contains a detailed description of your entire API. 
This file is essentially a resource listing of your API which adheres to OpenAPI Specification. 
The specification asks you to include information like:
What are all the operations that your API supports?
What are your API’s parameters and what does it return?
Does your API need some authorization?
###  [`Quote Controller`](./src/main/java/ca/jrvs/apps/trading/controller/QuoteController.java)
- High-level description for this controller. Where is market data coming from (IEX) and how did you cache the quote data (PSQL). Briefly talk about data from within your app
- GET `/quote/iex/addQuoteToDb/{ticker}`: Insert new quote entity into database, if it doest not exist. Using IEX trading API as market data source if quote already exist in database return updated/recent values for this quote entity
- GET `/quote/iex/ticker/{ticker}`: Show iex quote as a JSON object for a given ticker. The quote comes from iex's api
- PUT `/quote/iex/MarketData`: Update quote table against iex data by getting all quote entity in the table, getting their ticker symbol, finding the associated iex quote (recent live data from iex's api) and updating the quote values with the new iex values.
### [`Trader Controller`](./src/main/java/ca/jrvs/apps/trading/controller/TraderAccountController.java)
- High-level description for trader controller (e.g. it can manage trader and account information. it can deposit and withdraw fund from a given account)
- GET `/trader/firstname/{firstname}/lastname/{lastname}/dob/{dob}/country/{country}/email/{email}`: Creates a new account and associate it with a new trader. These new entities are saved in database
- GET `/trader/deposit/trader/{traderId}/amount/{amount}`: Add fund to the trader account
- PUT `/trader/withdraw/trader/{traderId}/amount/{amount}`: Withdraw fund to the trader account if the sum to removed is less than the account's balance### Order Controller

# Test
How did you test your application? Did you use any testing libraries? What's the code coverage?

# Deployment
- docker diagram including images, containers, network, and docker hub
  e.g. https://www.notion.so/jarviscanada/Dockerize-Trading-App-fc8c8f4167ad46089099fd0d31e3855d#6f8912f9438e4e61b91fe57f8ef896e0
- describe each image in details (e.g. how psql initialize tables)

# Improvements
If you have more time, what would you improve?
- Add support for linux env parameter passed to the app. currently the docker app does not accept any argument from the user. It would be best to let the user specify another postgres database name or specify a network host
- 
- 

