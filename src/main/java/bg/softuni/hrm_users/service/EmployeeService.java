package bg.softuni.hrm_users.service;


import bg.softuni.hrm_users.model.dto.AddEmployeeDTO;
import bg.softuni.hrm_users.model.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    boolean addEmployee(AddEmployeeDTO addEmployeeDTO);
    List<EmployeeDTO> getAllEmployees();
    void removeEmployee(long id);
    EmployeeDTO getEmployeeByID(long id);
    EmployeeDTO getEmployeeByIdentificationNumber(String number);
    void edithEmployee(EmployeeDTO employeeDTO);
}
