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

    @GetMapping("/all-positions")
    public ResponseEntity<List<String>> getAllPosition(){
        List<String> positionNames = positionService.getAllPositionName();

        return ResponseEntity.ok(positionNames);
    }

    @GetMapping()
    public ResponseEntity<List<PositionDTO>> getAllPositionsInDTOS(){
        List<PositionDTO> positionsDTOS = positionService.getAllPositionsDTOS();

        return ResponseEntity.ok(positionsDTOS);
    }

    @GetMapping("/all-employees/{id}")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeesDTOS(@PathVariable("id") long id){
        List<EmployeeDTO> employeeDTOS = positionService.allPositionEmployees(id);

        return ResponseEntity.ok(employeeDTOS);
    }

    @PostMapping("/add-employee/{idPos}")
    public ResponseEntity<Void> addEmployee(@PathVariable("idPos") Long idPos,
                                            @RequestBody PositionEmployeesDTO positionEmployeesDTO
    ){
        positionService.addEmployee(positionEmployeesDTO, idPos);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/employee/{idEm}/{idPos}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("idEm") Long idEm,
                                               @PathVariable("idPos")Long idPos){
        positionService.removeEmployee(idEm, idPos);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping()
    public ResponseEntity<Void> addPosition(@RequestBody AddPositionDTO addPositionDTO
                                            ){
        positionService.addNewPosition(addPositionDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionDTO> findPositionById(@PathVariable("id") long id){
        PositionDTO positionDTO = positionService.getPositionById(id);

        return ResponseEntity.ok(positionDTO);
    }

    @PostMapping("/edit")
    public ResponseEntity<PositionDTO> editPosition(
            @RequestBody PositionDTO positionDTO
    ){
        positionService.editPosition(positionDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable("id") Long id){
        positionService.removePosition(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
