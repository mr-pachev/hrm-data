package bg.softuni.hrm_users.service.impl;

import bg.softuni.hrm_users.model.dto.AddPositionDTO;
import bg.softuni.hrm_users.model.dto.EmployeeDTO;
import bg.softuni.hrm_users.model.dto.PositionDTO;
import bg.softuni.hrm_users.model.dto.PositionEmployeesDTO;
import bg.softuni.hrm_users.model.entity.Employee;
import bg.softuni.hrm_users.model.entity.Position;
import bg.softuni.hrm_users.model.entity.Project;
import bg.softuni.hrm_users.repository.EmployeeRepository;
import bg.softuni.hrm_users.repository.PositionRepository;
import bg.softuni.hrm_users.service.PositionService;
import bg.softuni.hrm_users.service.exception.ObjectNotFoundException;
import bg.softuni.hrm_users.util.EmployeeMapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<String> getAllPositionName() {;
        return positionRepository.findAll().stream()
                .map(Position::getPositionName)
                .collect(Collectors.toList());
    }

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

    @Override
    public void addNewPosition(AddPositionDTO addPositionDTO) {
        Position position = mapper.map(addPositionDTO, Position.class);

        positionRepository.save(position);
    }

    @Override
    public PositionDTO getPositionById(long id) {
        Position position = positionRepository.findById(id).orElseThrow(ObjectNotFoundException::new);

        return mapper.map(position, PositionDTO.class);
    }

    @Override
    public List<EmployeeDTO> allPositionEmployees(long id) {
       Position position = positionRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        List<Employee> employees = employeeRepository.findAllByPosition(position);

      return EmployeeMapperUtil.mapToEmployeeDTOS(employees);
    }

    @Override
    public void addEmployee(PositionEmployeesDTO positionEmployeesDTO, long idPos) {
        Position position = positionRepository.findById(idPos).orElseThrow(ObjectNotFoundException::new);

        String firstName = positionEmployeesDTO.getFullName().split(" ")[0];
        String middleName = positionEmployeesDTO.getFullName().split(" ")[1];
        String lastName = positionEmployeesDTO.getFullName().split(" ")[2];

        Employee newEmployee = employeeRepository.findByFirstNameAndMiddleNameAndLastName(firstName, middleName, lastName).orElseThrow(ObjectNotFoundException::new);

        List<Employee> employeesPosition = employeeRepository.findAllByPosition(position);

        for (Employee employee: employeesPosition) {
            if(Objects.equals(employee.getId(), newEmployee.getId())){
                return;
            }
        }
        newEmployee.setPosition(position);
        employeeRepository.save(newEmployee);

        employeesPosition.add(newEmployee);

       position.setEmployees(employeesPosition);

       positionRepository.save(position);
    }

    @Override
    @Transactional
    public void removeEmployee(long idEm, long idPos) {
        Position position = positionRepository.findById(idPos).orElseThrow(ObjectNotFoundException::new);
        Employee currentEmployee = employeeRepository.findById(idEm).orElseThrow(ObjectNotFoundException::new);

        position.getEmployees().remove(currentEmployee);
        // Премахване на позицията от служителя
        currentEmployee.setPosition(positionRepository.findByPositionName("DEFAULT_POSITION"));

        // Запазване на промените
        positionRepository.save(position);
        employeeRepository.save(currentEmployee);
    }
}
