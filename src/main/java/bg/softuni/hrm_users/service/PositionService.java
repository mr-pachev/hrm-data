package bg.softuni.hrm_users.service;

import bg.softuni.hrm_users.model.dto.*;

import java.util.List;

public interface PositionService {
    //get all positions names
    List<String> getAllPositionName();

    //get all positions
    List<PositionDTO> getAllPositionsDTOS();

    //add new position
    void addNewPosition(AddPositionDTO addPositionDTO);

    //get position by id
    PositionDTO getPositionById(long id);

    //edit position
    void editPosition(PositionDTO positionDTO);

    //delete position
    void removePosition(long id);

    //get all employees from current position
    List<EmployeeDTO> allPositionEmployees(long id);

    //add current employee in current position
    void addEmployee(EmployeeNameDTO employeeNameDTO, long idPos);

    //delete current employee from current position
    void removeEmployee(long idEm, long idPos);
}
