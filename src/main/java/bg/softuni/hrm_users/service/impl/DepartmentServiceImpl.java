package bg.softuni.hrm_users.service.impl;

import bg.softuni.hrm_users.model.dto.DepartmentDTO;
import bg.softuni.hrm_users.model.entity.Department;
import bg.softuni.hrm_users.model.entity.Project;
import bg.softuni.hrm_users.repository.DepartmentRepository;
import bg.softuni.hrm_users.repository.EmployeeRepository;
import bg.softuni.hrm_users.repository.ProjectRepository;
import bg.softuni.hrm_users.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    private final ProjectRepository projectRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, ProjectRepository projectRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<String> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(department -> department.getDepartmentName().name())
                .collect(Collectors.toList());
    }

    @Override
    public List<DepartmentDTO> getAllDepartmentsInDTOS() {
        List<Department> allDepartments = departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();

        for (Department department : allDepartments) {
            DepartmentDTO departmentDTO = mapToDepartmentDTO(department);
            departmentDTOS.add(departmentDTO);
        }

        return departmentDTOS;
    }

    private DepartmentDTO mapToDepartmentDTO(Department department){
        DepartmentDTO departmentDTO = new DepartmentDTO();

        department.setEmployees(employeeRepository.findAllByDepartment(department));
        department.setProjects(projectRepository.findAllByResponsibleDepartment(department));

        departmentDTO.setDepartmentName(department.getDepartmentName().name());
        String managerName = department.getManager().getFirstName() + " " +
                department.getManager().getMiddleName() + " " +
                department.getManager().getLastName();

        departmentDTO.setManager(managerName);

        departmentDTO.setEmployees(department.getEmployees().stream()
                .map(employee -> employee.getFirstName() + " " + employee.getMiddleName() + " " + employee.getLastName())
                .collect(Collectors.toList()));

        departmentDTO.setProjects(department.getProjects().stream()
                .map(Project::getName)
                .collect(Collectors.toList()));

        return departmentDTO;
    }

}
