package bg.softuni.hrm_users.service;

import bg.softuni.hrm_users.model.dto.*;

import java.util.List;

public interface PositionService {
    List<String> getAllPositionName();
    List<PositionDTO> getAllPositionsDTOS();
    void addNewPosition(AddPositionDTO addPositionDTO);
    PositionDTO getPositionById(long id);
    List<EmployeeDTO> allPositionEmployees(long id);
    void addEmployee(PositionEmployeesDTO positionEmployeesDTO, long idPos);
}
