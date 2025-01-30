# NetpipoBackend

Netpipo backend application contains Api operations for registration, retrieving,updating, delete employees.

## Technologies
    1. Java Spring Boot
    2. postregSQL
    3. Postman

## Application Set-Up and Configurations

### Configuration files

    - visit the git repository to get the project file
    url: https://github.com/ArseneDellamis/NetpipoBackend or clone https://github.com/ArseneDellamis/NetpipoBackend.git
    - extract the file
    - the file contains:
        1. employee_management
        2. netpipo_employee_database_v001(sql script)

### set IDE environment
- download and install any java ide(intellij, Eclipse, etc..)
- use postgresSQL for database connection
- after installation of DBMS upload the sqlScripts:
    1. netpipo_employee_database_v001(sql script)
- after installation of java ide navigate to the extracted file then open with the ide

## application Structure

### UserOps package
    
    **_1. AuthenticationConfig :_** contains the jwt configuration like token creation, token generation, claims extraction, etc...
    
    **_2. Configuration :_** contains security configuration like authentication and authorization and session management
    
    **_3. controller :_** contains authentication endpoints
    
    **_4. DaoRepository :_** data access Layer where database communication is happenning
    
    **_5. PayLoad :_** this contains client Request page for authentication and registration

    **_8. ResponseHandler :_** contains a custom class that handles HttpResponse showing status code and message
    
    **_9. service :_** contains authentication mechanism for generating a token for the authenticated or Registered user
    
    **_10. userEntities :_** contains classes mapped to the database bloguser and role tables

###  EmployeeOps
    **_0. readME:_** contains what to expect in the EmployeeOps
    **_1. manage:_** contains employee entity to manage
    **_2. daoRepository:_** for data assess object to use for access database operations
    **_3. service:_** service where database operations are implemented 
    **_4. controller:_** where RestAPIs are implimented containing endpoint to employee operations


### Dependencies
project dependencies available for the projects

| dependency        | usage                                        |
|-------------------|----------------------------------------------|
| spring data jpa   | relational database access                   |
| spring web        | creation of RestAPis                         |
| postgres driver   | postgres interaction                         |
| lombok            | getter,setter and constructor auto generation |
| spring validation | user validation                              |
| spring security   | user Authentication & authorization          |
| Junit 5           | junit test                                   |
| Mockito           | mock object for testing spring apps          |
| json web token    | token creation and generation                |


## Database configuration
- navigate to postgres(pgadmin/valentina studio)then upload the sql scripts
1. create database first
2. tables

| Database                  | table    |
|---------------------------|----------|
| netpipo_employee_database | net_user |
|                           | role     |
|                           | employee |

- also configure your database in intellij for connection navigate to resources/application.properties
  if you want to use your own database

### application.properties configuration for database
```plaintext
├── resources/
│   ├── static/
│   ├── templates/
└── ├── application.properties
```
  use the blow codes to establish connection to your database in your spring boot application
      
      spring.datasource.url=jdbc:postgresql://localhost:5432/database_name
      spring.datasource.username=your_username
      spring.datasource.password=your_password
      spring.datasource.driver-class-name=org.postgresql.Driver.

### *_N.B_* : if database dump are not being uploaded successfully just create a database under the name netpipo_employee_database then run the code in your IDE. spring boot will create tables for you


## RestApi and Endpoint
the application provides a list of endpoints for performing different task and are divided in 3 parts
1. Permitted RestApi
2. authenticated RestApi
3. HTTP methods access levels


### Permitted RestApi
these are Apis that are accessible by any user

| description                | RestApi                                                    |
|----------------------------|------------------------------------------------------------|
| user registration         |  http://localhost:8080/netpio/api/auth/register              |
| user authentication        | http://localhost:8080/netpio/api/auth/authenticate              |

### authenticated RestApi
these are only for authenticated and authorized users to perform action like create, update, read, delete
data of our employees

| description                | RestApi                                                    |
|----------------------------|------------------------------------------------------------|
| get all employees          | http://localhost:8080/netpipo/api/employees/               |
| create an employee         | http://localhost:8080/netpipo/api/employees/               |
| update an employee         | http://localhost:8080/netpipo/api/employees/{id}           |
| delete an employee         | http://localhost:8080/netpipo/api/employees/api/posts/{id} |
| get employee details by id | http://localhost:8080/netpipo/api/employees/{id}           |


### HTTP methods access levels

like put, post, get, delete have limited accessibility based on ROLES,
in other words "WHO CAN and WHO CAN'T"

    - PUT,POST,DELETE methods are reserved for any user with role of ADMIN
    - GET method is access by anyone with any role
