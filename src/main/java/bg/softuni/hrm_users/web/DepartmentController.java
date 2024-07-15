package bg.softuni.hrm_users.web;

import bg.softuni.hrm_users.model.dto.AddDepartmentDTO;
import bg.softuni.hrm_users.model.dto.DepartmentDTO;
import bg.softuni.hrm_users.model.dto.EmployeeDTO;
import bg.softuni.hrm_users.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<DepartmentDTO> findEmployeeById(@PathVariable("id") long id){

        return ResponseEntity.ok(departmentService.getDepartmentByID(id));
    }

    @PostMapping("/edit")
    public ResponseEntity<AddDepartmentDTO> editDepartment(
            @RequestBody AddDepartmentDTO addDepartmentDTO
            ){
        departmentService.editDepartment(addDepartmentDTO);

        return ResponseEntity.ok().build();
    }
}
