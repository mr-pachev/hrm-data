package bg.softuni.hrm_users.service.impl;

import bg.softuni.hrm_users.model.dto.DepartmentDTO;
import bg.softuni.hrm_users.model.dto.ProjectDTO;
import bg.softuni.hrm_users.model.entity.Department;
import bg.softuni.hrm_users.model.entity.Employee;
import bg.softuni.hrm_users.model.entity.Project;
import bg.softuni.hrm_users.repository.ProjectRepository;
import bg.softuni.hrm_users.service.ProjectService;
import bg.softuni.hrm_users.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ModelMapper mapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper mapper) {
        this.projectRepository = projectRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProjectDTO> getAllProjectsDTOS() {
        List<Project> allProjects = projectRepository.findAll();
        List<ProjectDTO> projectDTOS = new ArrayList<>();

        for (Project project : allProjects) {
            ProjectDTO projectDTO = new ProjectDTO();
            List<Employee> projectEmployees = projectRepository.findEmployeesByProjectId(project.getId());

            projectDTO.setId(project.getId());
            projectDTO.setName(project.getName());
            projectDTO.setDescription(project.getDescription());
            projectDTO.setStartDate(project.getStartDate().toString());
            projectDTO.setEndDate(project.getEndData().toString());
            projectDTO.setResponsibleDepartment(project.getResponsibleDepartment().getDepartmentName().name());

            List<String> employees = new ArrayList<>();
            for (Employee employee : projectEmployees) {
                String employeeFullName = employee.getFirstName() + " " +
                                        employee.getMiddleName() + " " +
                                        employee.getLastName();

                employees.add(employeeFullName);
            }

            projectDTO.setEmployees(employees);
            projectDTOS.add(projectDTO);
        }

        return projectDTOS;
    }

    @Override
    public ProjectDTO getProjectById(long id) {
        Project project = projectRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        ProjectDTO projectDTO = mapper.map(project, ProjectDTO.class);

        projectDTO.setResponsibleDepartment(project.getResponsibleDepartment().getDepartmentName().name());

        return projectDTO;
    }
}
