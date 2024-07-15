package bg.softuni.hrm_users.service;

import bg.softuni.hrm_users.model.dto.AddDepartmentDTO;
import bg.softuni.hrm_users.model.dto.DepartmentDTO;
import bg.softuni.hrm_users.model.dto.EmployeeDTO;

import java.util.List;

public interface DepartmentService {
    List<String> getAllDepartments();
    List<DepartmentDTO> getAllDepartmentsInDTOS();
    DepartmentDTO getDepartmentByID(long id);
    void editDepartment(AddDepartmentDTO addDepartmentDTO);
}
