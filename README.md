# Simple Store
A simple e-commerce web application. 

## Tech Stack 
Frontend: Vite/React

Backend: Springboot Server

Database: MySQL w/ JDBC


## How to run (development)
1. clone the repository

Frontend

2. cd into frontend
3. npm install (npm required for this)
4. npm run dev
5. There should be a link in the console output that brings you to the homepage
6. Press Ctrl+C to exit 

Backend (using intellij)

2. File --> New --> Project From Existing Sources
3. Import Project from external model --> Maven
4. Create
5. Create database in MySQL: CREATE DATABASE simplestore_db;
6. Add DB_USER and DB_PASSWORD to your environment variables before running (or use the username "simplestore" and password "password" to create a user and give them permissions for the database)
7. Run the SpringBootServer.java file to start the server 
9. stop the run to exit

Initialization:
1. Go to http://127.0.0.1:8080/api/admin/init to initialize the database with all the default values
2. Use the web application from Vite's URL as normal
