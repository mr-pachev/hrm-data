# HRM-Users - Rest Server
Rest Server -> https://github.com/mr-pachev/human-resource-managements.git -> containing the database and the business logic of the application.
For more effective presentation and easier testing of the application, a data.sql file has been added to the project, which loads the database with basic information about employees, positions, departments, and projects. Additionally, an init file has been created for loading with descriptions of positions and departments.

Upon the initial start of the Rest Server, the tables are created. After that, the application is stopped, and the setting sql -> init -> mode: always in application.yaml is changed. On the next start of the application, the tables are loaded with the data from the data.sql file. After loading, the application is stopped, and the setting sql -> init -> mode: always is changed back to never.

The application is ready for work and testing.
