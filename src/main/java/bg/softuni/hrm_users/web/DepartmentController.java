package bg.softuni.hrm_users.web;

import bg.softuni.hrm_users.model.dto.AddDepartmentDTO;
import bg.softuni.hrm_users.model.dto.DepartmentDTO;
import bg.softuni.hrm_users.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
        List<DepartmentDTO> departmentDTOS = departmentService.getAllDepartmentsInDTOS();

        return ResponseEntity.ok(departmentDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> findDepartmentById(@PathVariable("id") long id){

        return ResponseEntity.ok(departmentService.getDepartmentByID(id));
    }
    //edit department
    @PostMapping("/edit")
    public ResponseEntity<Void> editDepartment(
            @RequestBody DepartmentDTO departmentDTO
            ){
        departmentService.editDepartment(departmentDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("id") long id){
        departmentService.removeDepartment(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping()
    public ResponseEntity<Void> createDepartment(
            @RequestBody AddDepartmentDTO addDepartmentDTO
    ){
        departmentService.addDepartment(addDepartmentDTO);

        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .build()
                                .toUri()
                )
                .build();
    }
}
