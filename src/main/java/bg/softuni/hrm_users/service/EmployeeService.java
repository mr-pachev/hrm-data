package bg.softuni.hrm_users.service;


import bg.softuni.hrm_users.model.dto.AddEmployeeDTO;
import bg.softuni.hrm_users.model.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    //get all employees
    List<EmployeeDTO> getAllEmployees();

    //add new employee
    EmployeeDTO addEmployee(AddEmployeeDTO addEmployeeDTO);

    //get employee by id
    EmployeeDTO getEmployeeByID(long id);

    //edit employee
    void editEmployee(EmployeeDTO employeeDTO);

    //delete employee
    void removeEmployee(long id);
}
