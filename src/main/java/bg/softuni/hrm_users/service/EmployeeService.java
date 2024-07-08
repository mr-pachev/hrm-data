package bg.softuni.hrm_users.service;

import bg.softuni.hrm_users.model.dto.AddEmployeeDTO;

public interface EmployeeService {
    boolean addEmployee(AddEmployeeDTO addEmployeeDTO);
}
