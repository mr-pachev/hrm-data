package bg.softuni.hrm_users.service.impl;

import bg.softuni.hrm_users.model.dto.AddEmployeeDTO;
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

import java.util.Optional;

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
    public boolean addEmployee(AddEmployeeDTO addEmployeeDTO) {
        Employee employee = mapper.map(addEmployeeDTO, Employee.class);
        employee.setPosition(positionRepository.findByPositionName(PositionName.valueOf(addEmployeeDTO.getPosition())));
        employee.setDepartment(departmentRepository.findByDepartmentName(DepartmentName.valueOf(addEmployeeDTO.getDepartment())));
        employee.setEducation(educationRepository.findByEducationName(EducationName.valueOf(addEmployeeDTO.getEducation())));

        Optional<Employee> isExistEmployee = employeeRepository.findAllByIdentificationNumber(addEmployeeDTO.getIdentificationNumber());

        if(isExistEmployee.isPresent()){
            return false;
        }

        employeeRepository.save(employee);

        return true;
    }
}
