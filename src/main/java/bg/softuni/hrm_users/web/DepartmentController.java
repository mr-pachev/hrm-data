package bg.softuni.hrm_users.web;

import bg.softuni.hrm_users.model.dto.*;
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

    //get all departments
    @GetMapping()
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
        List<DepartmentDTO> departmentDTOS = departmentService.getAllDepartments();

        return ResponseEntity.ok(departmentDTOS);
    }

    //creat department
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

    //get department by id
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

    //delete department
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("id") long id){
        departmentService.removeDepartment(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    //get employees names from current department
    @GetMapping("/all-employees")
    public ResponseEntity<List<DepartmentEmployeeDTO>> getAllEmployees(){
        List<DepartmentEmployeeDTO> employees = departmentService.allEmployeesNames();

        return ResponseEntity.ok(employees);
    }

    //get employees from current project
    @GetMapping("/employees/{id}")
    public ResponseEntity<List<EmployeeDTO>> getAllDepartmentEmployees(@PathVariable("id") long id){
        List<EmployeeDTO> employeeDTOS = departmentService.allDepartmentEmployees(id);

        return ResponseEntity.ok(employeeDTOS);
    }

    //add employee in current department
    @PostMapping("/add-employee/{idDep}")
    public ResponseEntity<Void> addEmployee(@PathVariable("idDep") Long idDep,
                                            @RequestBody DepartmentEmployeeDTO departmentEmployeeDTO
    ){

        departmentService.addEmployee(departmentEmployeeDTO, idDep);

        return ResponseEntity.ok().build();
    }

}
