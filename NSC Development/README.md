# NSC Development Initial Test

- [Configuration](#configuration)
  - [Requirements](#requirements)
  - [Installation](#installation)
  - [Compilation](#compilation)
  - [Package and run](#package-and-run)
- [Functionality](#functionality)

## Configuration

### Requirements
- Java JDK 8
- Maven 3.6.3 (provided)
- PostgreSQL 15.1

### Installation

- Download and install Java JDK 8 from the [Java official webpage](https://www.oracle.com/cis/java/technologies/javase/javase8-archive-downloads.html)
- Set JAVA_HOME environment variable to point to the previous installation ([More details](https://docs.oracle.com/cd/E19182-01/821-0917/inst_jdk_javahome_t/index.html))
- Download and install PostgreSQL 15.1 from the [PostgreSQL official webpage](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)
  - Create user with username "postgres", password "password" and database name "userdb" ([Example](https://www.prisma.io/dataguide/postgresql/setting-up-a-local-postgresql-database))

### Compilation

```shell
.\mvnw compile
```

#### Package and run

```shell
.\mvnw spring-boot:run "-Dspring-boot.run.arguments=--app.controller.type=<controller-type>"
```

Where `<controller-type>` is `REST` or `UI`(by default).

## Functionality
The main goal is to develop a web application that allows us to manage users (CRUD operations)

User description: Username, Password, Firstname, Lastname, Birthdate, About, Address\
What commands can we execute:
1. Add user.
2. Update user.
3. Delete user.
4. Show full list of users.
5. Show specific information about user.

Depending on `app.controller.type` we have two methods to interact with program:
1. app.controller.type = REST
   1. GET `http://localhost:8080/users` - Will return full list of users
   2. GET `http://localhost:8080/users/{id}` - Will return information about specific user
   3. POST `http://localhost:8080/users` - Will create a new user (Body required)
   4. PUT `http://localhost:8080/users` - Will update information about user (Body required with id)
   5. DELETE `http://localhost:8080/users/{id}` - Will delete information about specific user
2. app.controller.type = UI
   1. Initial page to interact with UI is `http://localhost:8080`

Limits:\
Frameworks: Spring and Hibernate\
Database: PostgreSQL

The application must be built using Maven without installing or configuring any additional components;
The archive with the result of the test task should contain a readme.txt text file with instructions for building, configuring, \
configuring and deploying the application (if necessary).