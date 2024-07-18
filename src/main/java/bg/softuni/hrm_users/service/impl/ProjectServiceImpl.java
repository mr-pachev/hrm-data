package bg.softuni.hrm_users.service.impl;

import bg.softuni.hrm_users.model.dto.EmployeeDTO;
import bg.softuni.hrm_users.model.dto.ProjectDTO;
import bg.softuni.hrm_users.model.entity.Employee;
import bg.softuni.hrm_users.model.entity.Project;
import bg.softuni.hrm_users.model.enums.DepartmentName;
import bg.softuni.hrm_users.repository.DepartmentRepository;
import bg.softuni.hrm_users.repository.EmployeeRepository;
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
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ModelMapper mapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, ModelMapper mapper) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProjectDTO> getAllProjectsDTOS() {
        List<Project> allProjects = projectRepository.findAll();

        return getProjectDTOS(allProjects);
    }

    @Override
    public ProjectDTO getProjectById(long id) {
        Project project = projectRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        ProjectDTO projectDTO = mapper.map(project, ProjectDTO.class);

        projectDTO.setResponsibleDepartment(project.getResponsibleDepartment().getDepartmentName().name());

        return projectDTO;
    }

    @Override
    public void removeEmployee(long idEm, long idPr) {
        Project project = projectRepository.findById(idPr).orElseThrow(ObjectNotFoundException::new);
        Employee currentEmployee = employeeRepository.findById(idEm).orElseThrow(ObjectNotFoundException::new);

        project.getEmployees().remove(currentEmployee);
        currentEmployee.setProject(null);

        projectRepository.save(project);
        employeeRepository.save(currentEmployee);
    }

    @Override
    public void removeProject(long id) {
        List<Employee> projectEmployees = projectRepository.findEmployeesByProjectId(id);
        Project defaultProject = projectRepository.findById(1).orElseThrow(ObjectNotFoundException::new);

        for (Employee employee : projectEmployees) {
            employee.setProject(defaultProject);
        }

        projectRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> allProjectEmployees(long id) {
        List<Employee> projectEmployees = projectRepository.findEmployeesByProjectId(id);

        return mapToEmployeeDTOS(projectEmployees);
    }

    @Override
    public void editProject(ProjectDTO projectDTO) {
    Project project = map(projectDTO);
        projectRepository.save(project);
    }

    private List<ProjectDTO> getProjectDTOS(List<Project> allProjects) {
        List<ProjectDTO> projectDTOS = new ArrayList<>();

        for (Project project : allProjects) {
            ProjectDTO projectDTO = new ProjectDTO();

            projectDTO.setId(project.getId());
            projectDTO.setName(project.getName());
            projectDTO.setDescription(project.getDescription());
            projectDTO.setStartDate(project.getStartDate().toString());
            projectDTO.setEndDate(project.getEndData().toString());
            projectDTO.setResponsibleDepartment(project.getResponsibleDepartment().getDepartmentName().name());

            List<String> employees = mapToStringList(project.getId());

            projectDTO.setEmployees(employees);
            projectDTOS.add(projectDTO);
        }

        return projectDTOS;
    }

    private List<String> mapToStringList(long id) {
        List<Employee> projectEmployees = projectRepository.findEmployeesByProjectId(id);

        List<String> employees = new ArrayList<>();
        for (Employee employee : projectEmployees) {
            String employeeFullName = employee.getFirstName() + " " +
                    employee.getMiddleName() + " " +
                    employee.getLastName();

            employees.add(employeeFullName);
        }
        return employees;
    }

    private Project map(ProjectDTO projectDTO){
        Project project = projectRepository.findById(projectDTO.getId()).orElseThrow(ObjectNotFoundException::new);

        project.setName(projectDTO.getName());
        project.setDescription(project.getDescription());
        LocalDate startDate = mapper.map(projectDTO.getStartDate(), LocalDate.class);
        project.setStartDate(startDate);
        LocalDate endDate = mapper.map(projectDTO.getEndDate(), LocalDate.class);
        project.setEndData(endDate);

        project.setResponsibleDepartment(departmentRepository.findByDepartmentName(DepartmentName.valueOf(projectDTO.getResponsibleDepartment())));

        return project;
    }

    private List<EmployeeDTO> mapToEmployeeDTOS(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);
            employeeDTO.setPosition(employee.getPosition().getPositionName().name());
            employeeDTO.setDepartment(employee.getDepartment().getDepartmentName().name());
            employeeDTO.setEducation(employee.getEducation().getEducationName().name());

            employeeDTOS.add(employeeDTO);
        }

        return employeeDTOS;
    }
}
