package bg.softuni.hrm_users.service;

import bg.softuni.hrm_users.model.dto.*;

import java.util.List;

public interface ProjectService {

    //get all projects
    List<ProjectDTO> getAllProjectsDTOS();

    //add new project
    void creatProject(AddProjectDTO projectDTO);

    //get project by id
    ProjectDTO getProjectById(long id);

    //edit project
    void editProject(ProjectDTO projectDTO);

    //delete project
    void removeProject(long id);

    //get all employees from current project
    List<EmployeeDTO> allProjectEmployees(long id);

    //get all employees names
    List<ProjectEmployeeDTO> allEmployeesNames();

    //add current employee in current project
    void addEmployee(ProjectEmployeeDTO projectEmployeeDTO, long idPr);

    //delete current employee from current project
    void removeEmployee(long idEm, long idPr);
}
