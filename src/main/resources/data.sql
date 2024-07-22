-- Insert departments --
INSERT INTO departments (department_name, description, manager_id) VALUES ('EXECUTIVES', 'Responsible for the overall management and strategic direction of the company.', null);
INSERT INTO departments (department_name, description, manager_id) VALUES ('PROJECT_MANAGEMENT_DEPARTMENT', 'Responsible for planning, executing, and closing projects.', null);
INSERT INTO departments (department_name, description, manager_id) VALUES ('ADMINISTRATIVE_DEPARTMENT', 'Provides support services essential to the day-to-day operations of the company.', null);
INSERT INTO departments (department_name, description, manager_id) VALUES ('HR_DEPARTMENT', 'Manages the recruitment, training, development, and welfare of the company employees.', null);
INSERT INTO departments (department_name, description, manager_id) VALUES ('FINANCE_DEPARTMENT', 'Handles the financial planning, management, and record-keeping of the company.', null);
INSERT INTO departments (department_name, description, manager_id) VALUES ('MARKETING_DEPARTMENT', 'Focuses on promoting the company’s products or services.', null);
INSERT INTO departments (department_name, description, manager_id) VALUES ('IT_DEPARTMENT', 'Manages the company’s technology infrastructure.', null);
INSERT INTO departments (department_name, description, manager_id) VALUES ('CUSTOMER_SUPPORT_DEPARTMENT', 'Provides assistance and support to the company’s customers.', null);
INSERT INTO departments (department_name, description, manager_id) VALUES ('MAINTENANCE_DEPARTMENT', 'Performs cleaning, repair work and general maintenance of the company facilities.', null);
INSERT INTO departments (department_name, description, manager_id) VALUES ('DEFAULT_DEPARTMENT', 'Consists of employees with an unspecified department.', null);

-- Insert positions --
INSERT INTO positions (position_name, description) VALUES ('CHIEF_OPERATING_OFFICER', 'The COO oversees the day-to-day operations of the company. They ensure that the business is running efficiently and effectively, often managing departments like production, marketing, and human resources.');
INSERT INTO positions (position_name, description) VALUES ('CHIEF_EXECUTIVE_OFFICER', 'The CEO is responsible for the overall management and strategic direction of the company.');
INSERT INTO positions (position_name, description) VALUES ('CHIEF_FINANCIAL_OFFICER', 'The CFO manages the financial actions of the company. They oversee financial planning, financial risk management, record-keeping, and financial reporting.');
INSERT INTO positions (position_name, description) VALUES ('FINANCIAL_OFFICER', 'These duties help the financial officer to effectively manage the financial aspects and maintain the financial stability and sustainability of the business.');
INSERT INTO positions (position_name, description) VALUES ('HUMAN_RESOURCES_MANAGER', 'The HR Manager handles all aspects of human resources within the company. They recruit and onboard new employees.');
INSERT INTO positions (position_name, description) VALUES ('HEAD_OF_DEPARTMENT', 'The role of Head of Department is crucial in ensuring the effective functioning and success of their respective department within the broader organizational structure.');
INSERT INTO positions (position_name, description) VALUES ('IT_ADMINISTRATOR', 'The IT Administrator manages the company''s information technology infrastructure. They handle hardware and software installations, network security, system upgrades, and troubleshooting IT issues.');
INSERT INTO positions (position_name, description) VALUES ('PROJECT_MANAGER', 'Project Managers play a critical role in driving project success by effectively managing resources, risks, and stakeholder expectations throughout the project lifecycle.');
INSERT INTO positions (position_name, description) VALUES ('MARKETING_MANAGER', 'The Marketing Manager develops and implements marketing strategies to promote the company''s products or services.');
INSERT INTO positions (position_name, description) VALUES ('DEVELOPER', 'A developer is responsible for writing, testing, and maintaining code for software applications, websites, or other digital products.');
INSERT INTO positions (position_name, description) VALUES ('SENIOR_DEVELOPER', 'This position typically involves significant technical expertise and responsibility within a software development team or organization.');
INSERT INTO positions (position_name, description) VALUES ('LEAD_DEVELOPER', 'A Lead Developer is responsible for guiding and overseeing the technical aspects of software projects.');
INSERT INTO positions (position_name, description) VALUES ('QUALITY_ASSURANCE', 'Quality Assurance (QA) refers to a systematic process or set of activities designed to ensure that products or services meet specified requirements and standards.');
INSERT INTO positions (position_name, description) VALUES ('CUSTOMER_SUPPORT', 'Assist its customers in making cost-effective and correct use of a product or service.');
INSERT INTO positions (position_name, description) VALUES ('CLEANER', 'Person responsible for maintaining the cleanliness and hygiene of the office environment. Lays a crucial role in creating a pleasant and healthy work environment for office employees.');

-- Insert employees --
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Ivan', 'Petkov', 'Pachev', '1234567890', 40, '2017-09-07', 1, 1, 2);
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Dimitur', 'Ivanov', 'Borisov', '1345678901', 23, '2022-05-22', 7, 10, 2);
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Georgi', 'Dimov', 'Ivanov', '4567890123', 55, '2021-01-05', 1, 2, 2);
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Iana', 'Sergeeva', 'Petrova', '5678901234', 32, '2022-09-09', 2, 3, 2);
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Nikola', 'Dimitrov', 'Petrov', '3456789012', 45, '2023-04-19', 3, 5, 2);
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Vesela', 'Georgieva', 'Emilova', '6789012345', 42, '2020-11-18', 7, 10, 2);
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Milen', 'Nikolov', 'Biserov', '7890123456', 28, '2023-10-12', 8, 6, 2);
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Jana', 'Cvetanova', 'Kalinova', '8901234567', 31, '2024-10-08', 4, 2, 3);
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Evgenia', 'Petrova', 'Nikolova', '9012345678', 25, '2022-04-23', 2, 4, 2);
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Boris', 'Georgiev', 'Georgiev', '0123456789', 40, '2023-03-19', 7, 11, 2);
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Nikol', 'Petkova', 'Ianeva', '1357924680', 51, '2019-09-26', 5, 8, 3);
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Maria', 'Kalinova', 'Docova', '9876543210', 48, '2020-05-27', 9, 15, 1);
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Petur', 'Petrov', 'Dinev', '9870123456', 33, '2023-02-17', 5, 6, 2);
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Radostina', 'Biserova', 'Iordanova', '5432109876', 36, '2018-11-06', 7, 12, 3);
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Mariana', 'Doichinova', 'Vuleva', '9876012345', 39, '2023-10-05', 6, 9, 3);
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Cvetomir', 'Vulev', 'Kolarov', '6543217890', 45, '2022-06-21', 6, 6, 3);
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Petko', 'Petkov', 'Abadjiev', '8790654321', 43, '2024-03-11', 7, 7, 2);
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Ivan', 'Dimitrov', 'Saltirov', '7650987213', 26, '2021-01-16', 7, 10, 2);
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Dancho', 'Vladimirov', 'Genkov', '3258904761', 29, '2024-04-13', 7, 13, 2);
INSERT INTO employees (first_name, middle_name, last_name, identification_number, age, start_date, department_id, position_id, education_id)VALUES('Stanislava', 'Svetoslavova', 'Apostolova', '1234789605', 39, '2021-11-05', 7, 13, 2);

-- Update managers for each department --
UPDATE departments SET manager_id = 1 WHERE id = 1; -- EXECUTIVES
UPDATE departments SET manager_id = 4 WHERE id = 2; -- FINANCE_DEPARTMENT
UPDATE departments SET manager_id = 5 WHERE id = 3; -- HR_DEPARTMENT
UPDATE departments SET manager_id = 8 WHERE id = 4; -- ADMINISTRATIVE_DEPARTMENT
UPDATE departments SET manager_id = 13 WHERE id = 5; -- PROJECT_MANAGEMENT
UPDATE departments SET manager_id = 16 WHERE id = 6; -- MARKETING_DEPARTMENT
UPDATE departments SET manager_id = 14 WHERE id = 7; -- IT_DEPARTMENT
UPDATE departments SET manager_id = 7 WHERE id = 8; -- CUSTOMER_SUPPORT
UPDATE departments SET manager_id = 10 WHERE id = 9; -- MAINTENANCE_DEPARTMENT
UPDATE departments SET manager_id = 1 WHERE id = 10; -- DEFAULT_DEPARTMENT

-- Insert projects --
INSERT INTO projects (description, end_data, name, start_date, department_id) VALUES
                                                                                  ('Strategic Planning for FY2025', '2024-12-31', 'FY2025 Planning', '2024-01-01', 1),
                                                                                  ('Leadership Training Program', '2024-06-30', 'Leadership Training', '2024-03-01', 1),
                                                                                  ('Annual Financial Report', '2024-03-31', 'Financial Report 2023', '2024-01-01', 2),
                                                                                  ('Budgeting for FY2025', '2024-12-31', 'FY2025 Budgeting', '2024-05-01', 2),
                                                                                  ('Employee Onboarding', '2024-08-31', 'Onboarding New Hires', '2024-04-01', 3),
                                                                                  ('Employee Training Program', '2024-11-30', 'Skill Development Training', '2024-05-01', 3),
                                                                                  ('Office Supplies Procurement', '2024-09-30', 'Procurement Project', '2024-02-01', 4),
                                                                                  ('Facility Management System Upgrade', '2024-12-31', 'System Upgrade', '2024-06-01', 4),
                                                                                  ('Product Launch Project', '2024-12-15', 'New Product Launch', '2024-03-01', 5),
                                                                                  ('Website Redesign', '2024-07-30', 'Website Upgrade', '2024-01-15', 5),
                                                                                  ('Marketing Campaign for Q3', '2024-09-30', 'Q3 Marketing Campaign', '2024-06-01', 6),
                                                                                  ('Social Media Strategy', '2024-12-31', 'Social Media Plan', '2024-02-01', 6),
                                                                                  ('Network Security Improvement', '2024-10-31', 'Security Enhancement', '2024-01-15', 7),
                                                                                  ('Software Upgrade', '2024-08-30', 'System Software Update', '2024-03-01', 7),
                                                                                  ('Customer Feedback System', '2024-05-31', 'Feedback System Implementation', '2024-02-01', 8),
                                                                                  ('Customer Service Training', '2024-11-30', 'Service Improvement Training', '2024-07-01', 8),
                                                                                  ('Office Renovation', '2024-06-30', 'Renovation Project', '2024-01-01', 9),
                                                                                  ('Equipment Maintenance', '2024-12-31', 'Regular Maintenance', '2024-04-01', 9);

-- Relation between employees and projects --
INSERT INTO employees_projects (employee_id, project_id) VALUES (2, 13);
INSERT INTO employees_projects (employee_id, project_id) VALUES (4, 4);
INSERT INTO employees_projects (employee_id, project_id) VALUES (5, 5);
INSERT INTO employees_projects (employee_id, project_id) VALUES (6, 14);
INSERT INTO employees_projects (employee_id, project_id) VALUES (9, 7);
INSERT INTO employees_projects (employee_id, project_id) VALUES (10, 14);
INSERT INTO employees_projects (employee_id, project_id) VALUES (12, 9);
INSERT INTO employees_projects (employee_id, project_id) VALUES (13, 10);
INSERT INTO employees_projects (employee_id, project_id) VALUES (14, 13);
INSERT INTO employees_projects (employee_id, project_id) VALUES (16, 11);
INSERT INTO employees_projects (employee_id, project_id) VALUES (17, 12);
INSERT INTO employees_projects (employee_id, project_id) VALUES (18, 13);






