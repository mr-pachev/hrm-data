package bg.softuni.hrm_users.service.impl;

import bg.softuni.hrm_users.model.dto.EmployeeDTO;
import bg.softuni.hrm_users.model.entity.Employee;
import bg.softuni.hrm_users.model.enums.DepartmentName;
import bg.softuni.hrm_users.model.enums.EducationName;
import bg.softuni.hrm_users.model.enums.PositionName;
import bg.softuni.hrm_users.repository.DepartmentRepository;
import bg.softuni.hrm_users.repository.EducationRepository;
import bg.softuni.hrm_users.repository.EmployeeRepository;
import bg.softuni.hrm_users.repository.PositionRepository;
import bg.softuni.hrm_users.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {
        employeeRepository.save(mapToNewEmployee(employeeDTO));
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::mapToEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void removeEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDTO getEmployeeByID(long id) {
        Employee employee = employeeRepository.findById(id);
        return mapToEmployeeDTO(employee);
    }

    @Override
    public void edithEmployee(EmployeeDTO employeeDTO) {

        employeeRepository.save(mapToExistEmployee(employeeDTO));
    }

    public EmployeeDTO mapToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);
        employeeDTO.setPosition(employee.getPosition().getPositionName().name());
        employeeDTO.setDepartment(employee.getDepartment().getDepartmentName().name());
        employeeDTO.setEducation(employee.getEducation().getEducationName().name());

        return employeeDTO;
    }

    public Employee mapToNewEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();

        employee.setFirstName(employeeDTO.getFirstName());
        employee.setMiddleName(employeeDTO.getMiddleName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setIdentificationNumber(employeeDTO.getIdentificationNumber());
        employee.setAge(employeeDTO.getAge());

        LocalDate startDate = mapper.map(employeeDTO.getStartDate(), LocalDate.class);
        employee.setStartDate(startDate);

        employee.setPosition(positionRepository.findByPositionName(PositionName.valueOf(employeeDTO.getPosition())));
        employee.setDepartment(departmentRepository.findByDepartmentName(DepartmentName.valueOf(employeeDTO.getDepartment())));
        employee.setEducation((educationRepository.findByEducationName(EducationName.valueOf(employeeDTO.getEducation()))));

        return employee;
    }

    public Employee mapToExistEmployee(EmployeeDTO employeeDTO){
        Employee employee = employeeRepository.findByIdentificationNumber(employeeDTO.getIdentificationNumber());

        employee.setFirstName(employeeDTO.getFirstName());
        employee.setMiddleName(employeeDTO.getMiddleName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setIdentificationNumber(employeeDTO.getIdentificationNumber());
        employee.setAge(employeeDTO.getAge());

        LocalDate startDate = mapper.map(employeeDTO.getStartDate(), LocalDate.class);
        employee.setStartDate(startDate);

        employee.setPosition(positionRepository.findByPositionName(PositionName.valueOf(employeeDTO.getPosition())));
        employee.setDepartment(departmentRepository.findByDepartmentName(DepartmentName.valueOf(employeeDTO.getDepartment())));
        employee.setEducation((educationRepository.findByEducationName(EducationName.valueOf(employeeDTO.getEducation()))));

        return employee;
    }

    public Employee reMap(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findByIdentificationNumber(employeeDTO.getIdentificationNumber());

        employee.setFirstName(employeeDTO.getFirstName());
        employee.setMiddleName(employeeDTO.getMiddleName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setAge(employeeDTO.getAge());

        LocalDate startDate = mapper.map(employeeDTO.getStartDate(), LocalDate.class);
        employee.setStartDate(startDate);

        if (!employeeDTO.getEndDate().isEmpty()) {
            LocalDate endDate = mapper.map(employeeDTO.getEndDate(), LocalDate.class);
            employee.setEndDate(endDate);
        }

        employee.setPosition(positionRepository.findByPositionName(PositionName.valueOf(employeeDTO.getPosition())));
        employee.setDepartment(departmentRepository.findByDepartmentName(DepartmentName.valueOf(employeeDTO.getDepartment())));
        employee.setEducation(educationRepository.findByEducationName(EducationName.valueOf(employeeDTO.getEducation())));

        return employee;
    }
}
