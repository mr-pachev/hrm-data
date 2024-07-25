package bg.softuni.hrm_users.service;

import bg.softuni.hrm_users.model.dto.*;

import java.util.List;

public interface PositionService {
    List<String> getAllPositionName();
    List<PositionDTO> getAllPositionsDTOS();
    List<EmployeeDTO> allPositionEmployees(long id);
    PositionDTO getPositionById(long id);
    void addNewPosition(AddPositionDTO addPositionDTO);
    void editPosition(PositionDTO positionDTO);
    void removePosition(long id);
    void addEmployee(PositionEmployeesDTO positionEmployeesDTO, long idPos);
    void removeEmployee(long idEm, long idPos);
}
