package bg.softuni.hrm_users.service;

import bg.softuni.hrm_users.model.dto.DepartmentDTO;
import bg.softuni.hrm_users.model.dto.EmployeeDTO;
import bg.softuni.hrm_users.model.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {
    List<ProjectDTO> getAllProjectsDTOS();
    ProjectDTO getProjectById(long id);
    void removeEmployee(long idEm, long idPr);
    List<EmployeeDTO> allProjectEmployees(long id);
}
