package bg.softuni.hrm_users.service.impl;

import bg.softuni.hrm_users.model.dto.*;
import bg.softuni.hrm_users.model.entity.Department;
import bg.softuni.hrm_users.model.entity.Employee;
import bg.softuni.hrm_users.model.entity.Project;
import bg.softuni.hrm_users.repository.DepartmentRepository;
import bg.softuni.hrm_users.repository.EmployeeRepository;
import bg.softuni.hrm_users.repository.ProjectRepository;
import bg.softuni.hrm_users.service.DepartmentService;
import bg.softuni.hrm_users.service.exception.ObjectNotFoundException;
import bg.softuni.hrm_users.util.EmployeeMapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    //get all department names
    @Override
    public List<String> getAllDepartmentNames() {
        return departmentRepository.findAll().stream()
                .map(Department::getDepartmentName)
                .collect(Collectors.toList());
    }

    //get all departments
    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> allDepartments = departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();

        for (Department department : allDepartments) {
            DepartmentDTO departmentDTO = mapToDepartmentDTO(department);
            departmentDTOS.add(departmentDTO);
        }

        return departmentDTOS;
    }

    //check is exist department by name
    @Override
    public boolean isExistDepartment(String name) {
        return departmentRepository.existsByDepartmentName(name);
    }

    //add new department
    @Override
    public void addDepartment(AddDepartmentDTO addDepartmentDTO) {
        Department newDepartment = new Department();
        Employee manager = findEmployeeByFullName(addDepartmentDTO.getManager());

        newDepartment.setDepartmentName(addDepartmentDTO.getDepartmentName());
        newDepartment.setDescription(addDepartmentDTO.getDescriptions());
        newDepartment.setManager(manager);

        departmentRepository.save(newDepartment);
    }

    //get department by id
    @Override
    public DepartmentDTO getDepartmentByID(long id) {
        Department department = departmentRepository.findById(id);

        DepartmentDTO departmentDTO = mapToDepartmentDTO(department);

        return departmentDTO;
    }

    //edit department
    @Override
    public void editDepartment(DepartmentDTO departmentDTO) {
        Department editDepartment = departmentRepository.findById(departmentDTO.getId());
        Employee newManager = findEmployeeByFullName(departmentDTO.getManager());

        editDepartment.setDepartmentName(departmentDTO.getDepartmentName());
        editDepartment.setDescription(departmentDTO.getDescriptions());
        editDepartment.setManager(newManager);

        departmentRepository.save(editDepartment);

        List<Employee> departmentEmployees = editDepartment.getEmployees();
        for (Employee employee : departmentEmployees) {
            employee.setDepartment(editDepartment);
            employeeRepository.save(employee);
        }

        List<Project> departmentProjects = editDepartment.getProjects();
        for (Project project : departmentProjects) {
            project.setResponsibleDepartment(editDepartment);
            projectRepository.save(project);
        }
    }

    //delete department
    @Override
    public void removeDepartment(long id) {
        Department departmentForRemove = departmentRepository.findById(id);
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

    //get all employees from current department
    @Override
    public List<EmployeeDTO> allDepartmentEmployees(long id) {
        Department department = departmentRepository.findById(id);
        List<Employee> departmentEmployees = employeeRepository.findAllByDepartment(department);

        return EmployeeMapperUtil.mapToEmployeeDTOS(departmentEmployees);
    }

    //add current employee in current department
    @Override
    public void addEmployee(EmployeeNameDTO employeeNameDTO, long idDep) {
        Department department = departmentRepository.findById(idDep);

        String firstName = employeeNameDTO.getFullName().split(" ")[0];
        String middleName = employeeNameDTO.getFullName().split(" ")[1];
        String lastName = employeeNameDTO.getFullName().split(" ")[2];
        Employee employee = employeeRepository.findByFirstNameAndMiddleNameAndLastName(firstName, middleName, lastName).orElseThrow(ObjectNotFoundException::new);

        employee.setDepartment(department);
        employeeRepository.save(employee);

        List<Employee> employeesDepartment = department.getEmployees();

        employeesDepartment.add(employee);
        department.setEmployees(employeesDepartment);

        departmentRepository.save(department);
    }

    //delete employee from department
    @Override
    public void removeEmployee(long idEm, long idDep) {
        Department department = departmentRepository.findById(idDep);
        Employee currentEmployee = employeeRepository.findById(idEm).orElseThrow(ObjectNotFoundException::new);

        currentEmployee.setDepartment(departmentRepository.findByDepartmentName("DEFAULT_DEPARTMENT"));
        employeeRepository.save(currentEmployee);

        List<Employee> departmentEmployees = department.getEmployees();
        departmentEmployees.remove(currentEmployee);

        departmentRepository.save(department);
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
