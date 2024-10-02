
# Ecloud Systems FZE - CRUD Service

## Technology Stack
For building and running the application you need:
  - JDK 17
  - SpringBoot 3.3.4
  - Apache Maven 3.9.8
  - H2
  - JPA
 

## Enables you to do the following CRUD operations
#### 1.	Create User
     POST: localhost:8086/api/v1/users/
     Payload     {
                     "username":"Musni",
	                 "password":"password123"
                 }
#### 2.	Get list of users
     GET: localhost:8086/api/v1/users/
#### 3.	Get a User By ID
      GET: localhost:8086/api/v1/users/1
#### 4.	Update User 
      PUT: localhost:8086/api/v1/users/1
      Payload    {
                     "username":"hasan",
	                 "password":"password123",
                     "active": false
                 }
#### 5.	Delete User
      DELETE: localhost:8086/api/v1/users/1


