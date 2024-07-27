package bg.softuni.hrm_users.service.impl;

import bg.softuni.hrm_users.model.dto.AddPositionDTO;
import bg.softuni.hrm_users.model.dto.EmployeeDTO;
import bg.softuni.hrm_users.model.dto.PositionDTO;
import bg.softuni.hrm_users.model.dto.PositionEmployeesDTO;
import bg.softuni.hrm_users.model.entity.Employee;
import bg.softuni.hrm_users.model.entity.Position;
import bg.softuni.hrm_users.repository.EmployeeRepository;
import bg.softuni.hrm_users.repository.PositionRepository;
import bg.softuni.hrm_users.service.PositionService;
import bg.softuni.hrm_users.service.exception.ObjectNotFoundException;
import bg.softuni.hrm_users.util.EmployeeMapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public PositionServiceImpl(PositionRepository positionRepository, EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.positionRepository = positionRepository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    //get all positions names
    @Override
    public List<String> getAllPositionName() {
        ;
        return positionRepository.findAll().stream()
                .map(Position::getPositionName)
                .collect(Collectors.toList());
    }

    //get all positions
    @Override
    public List<PositionDTO> getAllPositionsDTOS() {
        List<Position> allPositions = positionRepository.findAll();
        List<PositionDTO> positionsDTOS = new ArrayList<>();

        for (Position position : allPositions) {
            PositionDTO positionDTO = mapper.map(position, PositionDTO.class);
            positionsDTOS.add(positionDTO);
        }

        return positionsDTOS;
    }

    //add new position
    @Override
    public void addNewPosition(AddPositionDTO addPositionDTO) {
        Position position = mapper.map(addPositionDTO, Position.class);

        positionRepository.save(position);
    }

    //get position by id
    @Override
    public PositionDTO getPositionById(long id) {
        Position position = positionRepository.findById(id).orElseThrow(ObjectNotFoundException::new);

        return mapper.map(position, PositionDTO.class);
    }

    //edit position
    @Override
    public void editPosition(PositionDTO positionDTO) {
        Position position = mapper.map(positionDTO, Position.class);

        position.setEmployees(setPositionInCurrentEmployee(position));

        positionRepository.save(position);
    }

    //delete position
    @Override
    public void removePosition(long id) {
        Position position = positionRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        removePositionInCurrentEmployee(position);

        positionRepository.deleteById(id);
    }

    //get all employees from current position
    @Override
    public List<EmployeeDTO> allPositionEmployees(long id) {
        Position position = positionRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        List<Employee> employees = employeeRepository.findAllByPosition(position);

        return EmployeeMapperUtil.mapToEmployeeDTOS(employees);
    }

    //add current employee in current position
    @Override
    public void addEmployee(PositionEmployeesDTO positionEmployeesDTO, long idPos) {
        Position position = positionRepository.findById(idPos).orElseThrow(ObjectNotFoundException::new);

        String firstName = positionEmployeesDTO.getFullName().split(" ")[0];
        String middleName = positionEmployeesDTO.getFullName().split(" ")[1];
        String lastName = positionEmployeesDTO.getFullName().split(" ")[2];

        Employee newEmployee = employeeRepository.findByFirstNameAndMiddleNameAndLastName(firstName, middleName, lastName).orElseThrow(ObjectNotFoundException::new);

        List<Employee> employeesPosition = employeeRepository.findAllByPosition(position);

        for (Employee employee : employeesPosition) {
            if (Objects.equals(employee.getId(), newEmployee.getId())) {
                return;
            }
        }
        newEmployee.setPosition(position);
        employeeRepository.save(newEmployee);

        employeesPosition.add(newEmployee);

        position.setEmployees(employeesPosition);

        positionRepository.save(position);
    }

    //delete current employee from current position
    @Override
    public void removeEmployee(long idEm, long idPos) {

        removeEmployeeFromPosition(idEm, idPos);
        updateEmployeePosition(idEm, "DEFAULT_POSITION");
    }

    private List<Employee> setPositionInCurrentEmployee(Position position) {
        List<Employee> employeesPosition = employeeRepository.findAllByPosition(position);

        for (Employee employee : employeesPosition) {
            employee.setPosition(position);
            employeeRepository.save(employee);
        }

        return employeesPosition;
    }

    private void removePositionInCurrentEmployee(Position position){
        List<Employee> employeesPosition = employeeRepository.findAllByPosition(position);

        for (Employee employee : employeesPosition) {
            employee.setPosition(positionRepository.findByPositionName("DEFAULT_POSITION"));
            employeeRepository.save(employee);
        }
    }

    private void removeEmployeeFromPosition(long idEm, long idPos) {
        Position position = positionRepository.findById(idPos).orElseThrow(ObjectNotFoundException::new);
        Employee currentEmployee = employeeRepository.findById(idEm).orElseThrow(ObjectNotFoundException::new);

        position.getEmployees().remove(currentEmployee);
        positionRepository.save(position);
    }

    private void updateEmployeePosition(long idEm, String defaultPositionName) {
        Employee currentEmployee = employeeRepository.findById(idEm).orElseThrow(ObjectNotFoundException::new);
        Position defaultPosition = positionRepository.findByPositionName(defaultPositionName);

        currentEmployee.setPosition(defaultPosition);
        employeeRepository.save(currentEmployee);
    }

}
