# Introduction

This project implement one of the most fundamental and frequent used Java libraries the "Java Database Connectivity (JDBC)" for the programming language Java, which defines how a client may access a database. It is a Java-based data access technology used for Java database connectivity.
The project use the DAO design pattern with a CRUD implementation, so it can be applied to other data sources, such as file systems, NoSQL, REST APIs, etc...
The application will be used to read and manage data from relational databases(Postgres).
In addition, this project explore some advanced JDBC concepts such as SQLException management, using stored procedures, order and limiting, sharding and partition database. 


# Design
![design er](https://github.com/jarviscanada/jarvis_data_eng_BikervensBernard/blob/jdbc/core_java/assets/ER.png?raw=true)

## **Postgres Database**

customer table
| Attribut | Description | Type |
| --- | --- | --- |
| `customer_id`| The unique identifier (PK) | bigint NOT NULL DEFAULT nextval('hp_customer_seq') PRIMARY KEY|
| `first_name`| customer's first name | varchar(50) DEFAULT NULL|
| `last_name`| customer's last name | varchar(50) DEFAULT NULL|
| `email`| customers's email | varchar(50) DEFAULT NULL|
| `phone`| customers's phone | varchar(50) DEFAULT NULL|
| `address`| customers's city | varchar(50) DEFAULT NULL|
| `city`| customers's city | varchar(50) DEFAULT NULL|
| `state`| customers's state | varchar(50) DEFAULT NULL|
| `zipcode`| customers's zide code | varchar(50) DEFAULT NULL|

salesperson table
| Attribut | Description | Type |
| --- | --- | --- |
| `salesperson_id`| The unique identifier (PK) | bigint NOT NULL DEFAULT nextval('hp_salesperson_seq') PRIMARY KEY|
| `first_name`| salesperson's name | varchar(50) DEFAULT NULL|
| `last_name`| salesperson's name | varchar(50) DEFAULT NULL|
| `email`| salesperson's email | varchar(50) DEFAULT NULL|
| `phone`| salesperson's phone | varchar(50) DEFAULT NULL|
| `address`| salesperson's city | varchar(50) DEFAULT NULL|
| `city`| salesperson's city | varchar(50) DEFAULT NULL|
| `state`| salesperson's state | varchar(50) DEFAULT NULL|
| `zipcode`| salesperson's zide code | varchar(50) DEFAULT NULL|

orders table
| Attribut | Description | Type |
| --- | --- | --- |
| `order_id`| The unique identifier (PK) | bigint NOT NULL DEFAULT nextval('hp_order_seq') PRIMARY KEY|
| `creation_date`| data of the order | timestamp DEFAULT NULL|
| `total_due`| total amount in $ | numeric(10,2) DEFAULT NULL|
| `status`| paid, not paid or canceled | NOT NULL|
| `customer_id`| customers's unique identifier (FK) REFERENCES customer(customer_id) | NOT NULL|
| `salesperson_id`| salesperson's unique identifier (FK) REFERENCES salesperson(salesperson_id)| NOT NULL|

order_item table
| Attribut | Description | Type |
| --- | --- | --- |
| `order_item_id`| The unique identifier (PK) | bigint NOT NULL DEFAULT nextval('hp_orderline_seq') PRIMARY KEY|
| `order_id`| unique identifier (FK) REFERENCES orders(order_id) | NOT NULL|
| `product_id`| unique identifier (FK) REFERENCES product(product_id) | NOT NULL|
| `quantity`| how many of this item is being ordered | int DEFAULT NULL|

product table
| Attribut | Description | Type |
| --- | --- | --- |
| `product_id`| The unique identifier (PK) | bigint NOT NULL DEFAULT nextval('hp_product_seq') PRIMARY KEY|
| `code`| product's SKU | varchar(50) DEFAULT NULL|
| `name`| product name | varchar(50) DEFAULT NULL|
| `size`| amount of product (measuring unit per fabricant) | int DEFAULT NULL|
| `variety`| family group i.e. juice | DEFAULT NULL|
| `price`| product price | DEFAULT NULL|
| `status`| ACTIVE/ DISCONTINUED | DEFAULT NULL|
  
# Order of operations
These commands are for linux/Mac, changes will need to made if you are running this in Microsoft Windows.

## Prerequisites
Docker is installed
psql client is installed

## Actions

### Running PostgreSQL
1. Pull Docker Image
`docker pull postgres`

2. Build data directory
`mkdir -p ~/srv/postgres`

3. Run docker image
`docker run --rm --name lil-postgres -e POSTGRES_PASSWORD=password -d -v $HOME/srv/postgres:/var/lib/postgresql/data -p 5432:5432 postgres`

### Stopping PostgreSQL
`docker stop lil-postgres`

### Logging into Database
* `psql -h localhost -U postgres -d hplussport`

### Creating starter data
1. `psql -h localhost -U postgres -f database.sql`
2. `psql -h localhost -U postgres -d hplussport -f customer.sql`
3. `psql -h localhost -U postgres -d hplussport -f product.sql`
4. `psql -h localhost -U postgres -d hplussport -f salesperson.sql`
5. `psql -h localhost -U postgres -d hplussport -f orders.sql`


### Creating stored procedure
1. `psql -h localhost -U postgres -f stored_proc.sql`

