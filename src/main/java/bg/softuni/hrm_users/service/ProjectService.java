package bg.softuni.hrm_users.service;

import bg.softuni.hrm_users.model.dto.*;

import java.util.List;

public interface ProjectService {

    void creatProject(AddProjectDTO projectDTO);
    List<ProjectDTO> getAllProjectsDTOS();
    ProjectDTO getProjectById(long id);
    void removeEmployee(long idEm, long idPr);
    void removeProject(long id);
    List<EmployeeDTO> allProjectEmployees(long id);
    List<ProjectEmployeeDTO> allEmployeesNames();
    void editProject(ProjectDTO projectDTO);
    void addEmployee(ProjectEmployeeDTO projectEmployeeDTO, long idPr);
}
