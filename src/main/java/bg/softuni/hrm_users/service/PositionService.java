package bg.softuni.hrm_users.service;

import bg.softuni.hrm_users.model.dto.AddPositionDTO;
import bg.softuni.hrm_users.model.dto.DepartmentDTO;
import bg.softuni.hrm_users.model.dto.EmployeeDTO;
import bg.softuni.hrm_users.model.dto.PositionDTO;

import java.util.List;

public interface PositionService {
    List<String> getAllPositionName();
    List<PositionDTO> getAllPositionsDTOS();
    void addNewPosition(AddPositionDTO addPositionDTO);
    PositionDTO getPositionById(long id);
    List<EmployeeDTO> allPositionEmployees(long id);
}
