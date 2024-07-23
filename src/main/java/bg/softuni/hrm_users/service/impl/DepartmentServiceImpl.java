package bg.softuni.hrm_users.service.impl;

import bg.softuni.hrm_users.model.dto.AddDepartmentDTO;
import bg.softuni.hrm_users.model.dto.DepartmentDTO;
import bg.softuni.hrm_users.model.entity.Department;
import bg.softuni.hrm_users.model.entity.Employee;
import bg.softuni.hrm_users.model.entity.Project;
import bg.softuni.hrm_users.repository.DepartmentRepository;
import bg.softuni.hrm_users.repository.EmployeeRepository;
import bg.softuni.hrm_users.repository.ProjectRepository;
import bg.softuni.hrm_users.service.DepartmentService;
import bg.softuni.hrm_users.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper mapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, ProjectRepository projectRepository, ModelMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.mapper = mapper;
    }

    @Override
    public List<String> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(Department::getDepartmentName)
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

    @Override
    public DepartmentDTO getDepartmentByID(long id) {
        Department department = departmentRepository.findById(id).orElseThrow(ObjectNotFoundException::new);

        DepartmentDTO departmentDTO = mapToDepartmentDTO(department);

        return departmentDTO;
    }

    @Override
    public void editDepartment(AddDepartmentDTO addDepartmentDTO) {
        Department department = departmentRepository.findByDepartmentName(addDepartmentDTO.getDepartmentName());
        Employee newManager = findEmployeeByFullName(addDepartmentDTO.getManager());

        department.setManager(newManager);
        department.setEmployees(employeeRepository.findAllByDepartment(department));
        department.setProjects(projectRepository.findAllByResponsibleDepartment(department));

        departmentRepository.save(department);
    }

    @Override
    public void removeDepartment(long id) {
        Department departmentForRemove = departmentRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        Department defaultDep = departmentRepository.findByDepartmentName("DEFAULT_DEPARTMENT");


        for (Employee employee : departmentForRemove.getEmployees()) {
            employee.setDepartment(defaultDep);
            employeeRepository.save(employee);
        }

        for (Project project : departmentForRemove.getProjects()) {
            project.setResponsibleDepartment(defaultDep);
            projectRepository.save(project);
        }

        departmentRepository.delete(departmentForRemove);
    }

    @Override
    public void addDepartment(AddDepartmentDTO addDepartmentDTO) {
        Department newDepartment = new Department();
        Employee manager = findEmployeeByFullName(addDepartmentDTO.getManager());

        newDepartment.setDepartmentName(addDepartmentDTO.getDepartmentName());
        newDepartment.setDescription(addDepartmentDTO.getDescriptions());
        newDepartment.setManager(manager);

        departmentRepository.save(newDepartment);
    }

    @Override
    public boolean isExistDepartment(String name) {
        return departmentRepository.existsByDepartmentName(name);
    }

    private DepartmentDTO mapToDepartmentDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();

        department.setEmployees(employeeRepository.findAllByDepartment(department));
        department.setProjects(projectRepository.findAllByResponsibleDepartment(department));

        departmentDTO.setDepartmentName(department.getDepartmentName());
        String managerName = department.getManager().getFirstName() + " " +
                department.getManager().getMiddleName() + " " +
                department.getManager().getLastName();

        departmentDTO.setId(department.getId());
        departmentDTO.setDescriptions(department.getDescription());
        departmentDTO.setManager(managerName);
        departmentDTO.setIdentificationNumber(department.getManager().getIdentificationNumber());

        departmentDTO.setEmployees(department.getEmployees().stream()
                .map(employee -> employee.getFirstName() + " " + employee.getMiddleName() + " " + employee.getLastName())
                .collect(Collectors.toList()));

        departmentDTO.setProjects(department.getProjects().stream()
                .map(Project::getName)
                .collect(Collectors.toList()));

        return departmentDTO;
    }

    private Employee findEmployeeByFullName (String fullName){
        String firstName = fullName.split(" ")[0];
        String middleName = fullName.split(" ")[1];
        String lastName = fullName.split(" ")[2];

        return employeeRepository.findByFirstNameAndMiddleNameAndLastName(firstName, middleName, lastName).orElseThrow(ObjectNotFoundException::new);
    }
}
