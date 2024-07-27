package bg.softuni.hrm_users.web;

import bg.softuni.hrm_users.model.dto.*;
import bg.softuni.hrm_users.service.PositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/positions")
public class PositionController {
    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    //get all positions names
    @GetMapping("/all-positions")
    public ResponseEntity<List<String>> getAllPositionName(){
        List<String> positionNames = positionService.getAllPositionName();

        return ResponseEntity.ok(positionNames);
    }

    //get all positions
    @GetMapping()
    public ResponseEntity<List<PositionDTO>> getAllPositionsInDTOS(){
        List<PositionDTO> positionsDTOS = positionService.getAllPositionsDTOS();

        return ResponseEntity.ok(positionsDTOS);
    }

    //add new position
    @PostMapping()
    public ResponseEntity<Void> addPosition(@RequestBody AddPositionDTO addPositionDTO
    ){
        positionService.addNewPosition(addPositionDTO);

        return ResponseEntity.ok().build();
    }

    //get position by id
    @GetMapping("/{id}")
    public ResponseEntity<PositionDTO> findPositionById(@PathVariable("id") long id){
        PositionDTO positionDTO = positionService.getPositionById(id);

        return ResponseEntity.ok(positionDTO);
    }

    //edit position
    @PostMapping("/edit")
    public ResponseEntity<PositionDTO> editPosition(
            @RequestBody PositionDTO positionDTO
    ){
        positionService.editPosition(positionDTO);

        return ResponseEntity.ok().build();
    }

    //delete position
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable("id") Long id){
        positionService.removePosition(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    //get all employees from current position
    @GetMapping("/all-employees/{id}")
    public ResponseEntity<List<EmployeeDTO>> getAllPositionEmployeesDTOS(@PathVariable("id") long id){
        List<EmployeeDTO> employeeDTOS = positionService.allPositionEmployees(id);

        return ResponseEntity.ok(employeeDTOS);
    }

    //add current employee in current position
    @PostMapping("/add-employee/{idPos}")
    public ResponseEntity<Void> addEmployee(@PathVariable("idPos") Long idPos,
                                            @RequestBody PositionEmployeesDTO positionEmployeesDTO
    ){
        positionService.addEmployee(positionEmployeesDTO, idPos);

        return ResponseEntity.ok().build();
    }

    //delete current employee from current position
    @DeleteMapping("/employee/{idEm}/{idPos}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("idEm") Long idEm,
                                               @PathVariable("idPos")Long idPos){
        positionService.removeEmployee(idEm, idPos);

        return ResponseEntity
                .noContent()
                .build();
    }
}
