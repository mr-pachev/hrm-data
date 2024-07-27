package bg.softuni.hrm_users.service.impl;

import bg.softuni.hrm_users.model.dto.AddEmployeeDTO;
import bg.softuni.hrm_users.model.dto.EmployeeDTO;
import bg.softuni.hrm_users.model.dto.EmployeeNameDTO;
import bg.softuni.hrm_users.model.entity.Employee;
import bg.softuni.hrm_users.model.enums.EducationName;
import bg.softuni.hrm_users.repository.DepartmentRepository;
import bg.softuni.hrm_users.repository.EducationRepository;
import bg.softuni.hrm_users.repository.EmployeeRepository;
import bg.softuni.hrm_users.repository.PositionRepository;
import bg.softuni.hrm_users.service.EmployeeService;
import bg.softuni.hrm_users.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final ModelMapper mapper;
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;
    private final EducationRepository educationRepository;

    public EmployeeServiceImpl(ModelMapper mapper, EmployeeRepository employeeRepository, PositionRepository positionRepository, DepartmentRepository departmentRepository, EducationRepository educationRepository) {
        this.mapper = mapper;
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.departmentRepository = departmentRepository;
        this.educationRepository = educationRepository;
    }

    //get all employees names
    @Override
    public List<EmployeeNameDTO> allEmployeesNames() {
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeNameDTO> departmentEmployeeDTOS = new ArrayList<>();
        for (Employee employee : employees) {
            String fullName = employee.getFirstName() + " " +
                    employee.getMiddleName()  + " " +
                    employee.getLastName();
            EmployeeNameDTO employeeNameDTO = new EmployeeNameDTO();
            employeeNameDTO.setId(employee.getId());
            employeeNameDTO.setFullName(fullName);

            departmentEmployeeDTOS.add(employeeNameDTO);
        }

        return departmentEmployeeDTOS;
    }

    //get all employees
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::mapToEmployeeDTO)
                .collect(Collectors.toList());
    }

    //add new employee
    @Override
    public EmployeeDTO addEmployee(AddEmployeeDTO addEmployeeDTO) {
        Employee employee = employeeRepository.save(mapToNewEmployee(addEmployeeDTO));
      return reMap(employee);
    }

    //get employee by id
    @Override
    public EmployeeDTO getEmployeeByID(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(ObjectNotFoundException::new);

        return mapToEmployeeDTO(employee);
    }

    //edit employee
    @Override
    public void editEmployee(EmployeeDTO employeeDTO) {

        employeeRepository.save(mapToExistEmployee(employeeDTO));
    }

    //delete employee
    @Override
    public void removeEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDTO mapToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);
        employeeDTO.setPosition(employee.getPosition().getPositionName());
        employeeDTO.setDepartment(employee.getDepartment().getDepartmentName());
        employeeDTO.setEducation(employee.getEducation().getEducationName().name());

        return employeeDTO;
    }

    private Employee mapToNewEmployee(AddEmployeeDTO addEmployeeDTO){
        Employee employee = new Employee();

        employee.setFirstName(addEmployeeDTO.getFirstName());
        employee.setMiddleName(addEmployeeDTO.getMiddleName());
        employee.setLastName(addEmployeeDTO.getLastName());
        employee.setIdentificationNumber(addEmployeeDTO.getIdentificationNumber());
        employee.setAge(addEmployeeDTO.getAge());

        LocalDate startDate = mapper.map(addEmployeeDTO.getStartDate(), LocalDate.class);
        employee.setStartDate(startDate);

        employee.setPosition(positionRepository.findByPositionName(addEmployeeDTO.getPosition()));
        employee.setDepartment(departmentRepository.findByDepartmentName(addEmployeeDTO.getDepartment()));
        employee.setEducation((educationRepository.findByEducationName(EducationName.valueOf(addEmployeeDTO.getEducation()))));

        return employee;
    }

    private Employee mapToExistEmployee(EmployeeDTO employeeDTO){
        Employee employee = employeeRepository.findByIdentificationNumber(employeeDTO.getIdentificationNumber());

        employee.setFirstName(employeeDTO.getFirstName());
        employee.setMiddleName(employeeDTO.getMiddleName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setIdentificationNumber(employeeDTO.getIdentificationNumber());
        employee.setAge(employeeDTO.getAge());

        LocalDate startDate = mapper.map(employeeDTO.getStartDate(), LocalDate.class);
        employee.setStartDate(startDate);

        employee.setPosition(positionRepository.findByPositionName(employeeDTO.getPosition()));
        employee.setDepartment(departmentRepository.findByDepartmentName(employeeDTO.getDepartment()));
        employee.setEducation((educationRepository.findByEducationName(EducationName.valueOf(employeeDTO.getEducation()))));

        return employee;
    }

    private EmployeeDTO reMap(Employee employee) {
        EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);

        employeeDTO.setPosition(employee.getPosition().getPositionName());
        employeeDTO.setDepartment(employee.getDepartment().getDepartmentName());
        employeeDTO.setEducation(employee.getEducation().getEducationName().name());

        return employeeDTO;
    }
}
