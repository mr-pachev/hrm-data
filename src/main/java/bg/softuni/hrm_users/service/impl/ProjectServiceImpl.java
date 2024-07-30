package bg.softuni.hrm_users.service.impl;

import bg.softuni.hrm_users.model.dto.*;
import bg.softuni.hrm_users.model.entity.Employee;
import bg.softuni.hrm_users.model.entity.Project;
import bg.softuni.hrm_users.repository.DepartmentRepository;
import bg.softuni.hrm_users.repository.EmployeeRepository;
import bg.softuni.hrm_users.repository.ProjectRepository;
import bg.softuni.hrm_users.service.ProjectService;
import bg.softuni.hrm_users.service.exception.ObjectNotFoundException;
import bg.softuni.hrm_users.util.EmployeeMapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    //get all projects
    @Override
    public List<ProjectDTO> getAllProjectsDTOS() {
        List<Project> allProjects = projectRepository.findAll();

        return getProjectDTOS(allProjects);
    }

    //add new project
    @Override
    public void creatProject(AddProjectDTO addProjectDTO) {
        Project project = mapper.map(addProjectDTO, Project.class);

        LocalDate endDate = mapper.map(addProjectDTO.getEndDate(), LocalDate.class);
        project.setEndData(endDate);
        project.setResponsibleDepartment(departmentRepository.findByDepartmentName(addProjectDTO.getResponsibleDepartment()));

        projectRepository.save(project);
    }

    //get project by id
    @Override
    public ProjectDTO getProjectById(long id) {
        Project project = projectRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        ProjectDTO projectDTO = mapper.map(project, ProjectDTO.class);

        projectDTO.setResponsibleDepartment(project.getResponsibleDepartment().getDepartmentName());

        return projectDTO;
    }

    //edit project
    @Override
    public void editProject(ProjectDTO projectDTO) {
        Project project = map(projectDTO);
        projectRepository.save(project);
    }

    //delete project
    @Override
    public void removeProject(long id) {
        List<Employee> projectEmployees = projectRepository.findEmployeesByProjectId(id);
        Project project = projectRepository.findById(id).orElseThrow(ObjectNotFoundException::new);

        for (Employee employee : projectEmployees) {
            employee.getProjects().remove(project);
        }

        projectRepository.deleteById(id);
    }

    //get all employees from current project
    @Override
    public List<EmployeeDTO> allProjectEmployees(long id) {
        List<Employee> projectEmployees = projectRepository.findEmployeesByProjectId(id);

        return EmployeeMapperUtil.mapToEmployeeDTOS(projectEmployees);
    }

    //add employee in current project
    @Override
    public void addEmployee(EmployeeNameDTO employeeNameDTO, long idPr) {
        Project project = projectRepository.findById(idPr).orElseThrow(ObjectNotFoundException::new);

        String firstName = employeeNameDTO.getFullName().split(" ")[0];
        String middleName = employeeNameDTO.getFullName().split(" ")[1];
        String lastName = employeeNameDTO.getFullName().split(" ")[2];
        Employee employee = employeeRepository.findByFirstNameAndMiddleNameAndLastName(firstName, middleName, lastName).orElseThrow(ObjectNotFoundException::new);

        List<Project> employeeProjects = employee.getProjects();

        for (Project employeeProject : employeeProjects) {
            if(Objects.equals(employeeProject.getId(), project.getId())){
                return;
            }
        }

        employeeProjects.add(project);
        employee.setProjects(employeeProjects);
        employeeRepository.save(employee);

        List<Employee> employees = project.getEmployees();
        employees.add(employee);
        project.setEmployees(employees);
        projectRepository.save(project);
    }

    //delete current employee from current project
    @Override
    @Transactional
    public void removeEmployee(long idEm, long idPr) {
        Project project = projectRepository.findById(idPr).orElseThrow(ObjectNotFoundException::new);
        Employee currentEmployee = employeeRepository.findById(idEm).orElseThrow(ObjectNotFoundException::new);

        project.removeEmployee(currentEmployee);

        projectRepository.save(project);
        employeeRepository.save(currentEmployee);
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
            projectDTO.setResponsibleDepartment(project.getResponsibleDepartment().getDepartmentName());

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
            String employeeFullName = employee.toString();

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

        project.setResponsibleDepartment(departmentRepository.findByDepartmentName(projectDTO.getResponsibleDepartment()));

        return project;
    }
}
