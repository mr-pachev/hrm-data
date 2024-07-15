package bg.softuni.hrm_users.service;

import bg.softuni.hrm_users.model.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    List<String> getAllDepartments();
    List<DepartmentDTO> getAllDepartmentsInDTOS();

    DepartmentDTO getDepartmentByID(long id);
}
