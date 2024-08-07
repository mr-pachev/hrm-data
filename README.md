# HRM-Data - Rest Server
For the purposes of this demo, the project runs as a REST server, communicating via REST requests with [ (https://github.com/mr-pachev/human-resource-managements.git) ], which runs as a REST client.

Rest Server -> containing the database and the business logic of the application.
For more effective presentation and easier testing of the application, a data.sql file has been added to the project, which loads the database with basic information about employees, positions, departments, and projects. Additionally, an init file has been created for loading with descriptions of education.

Upon the initial start of the Rest Server, the tables are created. After that, the application is stopped, and the setting sql -> init -> mode: always in application.yaml is changed. On the next start of the application, the tables are loaded with the data from the data.sql file. After loading, the application is stopped, and the setting sql -> init -> mode: always is changed back to never.

# Only existing employees can use the system.
To register a new user, the employee's personal identification number from the database is required. The first registered user has administrator rights. For example, use the following social security number: 1208117890.

The application is ready for work and testing.
