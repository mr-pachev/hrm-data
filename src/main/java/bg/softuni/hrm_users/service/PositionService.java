package bg.softuni.hrm_users.service;

import bg.softuni.hrm_users.model.dto.DepartmentDTO;
import bg.softuni.hrm_users.model.dto.PositionDTO;

import java.util.List;

public interface PositionService {
    List<String> getAllPositionName();
    List<PositionDTO> getAllPositionsDTOS();
}
