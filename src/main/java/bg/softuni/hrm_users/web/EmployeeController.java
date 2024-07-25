package bg.softuni.hrm_users.web;

import bg.softuni.hrm_users.model.dto.AddEmployeeDTO;
import bg.softuni.hrm_users.model.dto.EmployeeDTO;
import bg.softuni.hrm_users.service.DepartmentService;
import bg.softuni.hrm_users.service.EducationService;
import bg.softuni.hrm_users.service.EmployeeService;
import bg.softuni.hrm_users.service.PositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final PositionService positionService;
    private final DepartmentService departmentService;
    private final EducationService educationService;
    private final EmployeeService employeeService;

    public EmployeeController(PositionService positionService, DepartmentService departmentService, EducationService educationService, EmployeeService employeeService) {
        this.positionService = positionService;
        this.departmentService = departmentService;
        this.educationService = educationService;
        this.employeeService = employeeService;
    }

    @GetMapping("/all-positions")
    public ResponseEntity<List<String>> getAllPosition(){
       List<String> positionNames = positionService.getAllPositionName();

        return ResponseEntity.ok(positionNames);
    }
    @GetMapping("/all-departments")
    public ResponseEntity<List<String>> getAllDepartments(){
       List<String> departmentNames = departmentService.getAllDepartmentNames();

        return ResponseEntity.ok(departmentNames);
    }

    @GetMapping("/all-educations")
    public ResponseEntity<List<String>> getAllEducations(){
        List<String> educationNames = educationService.getAllEducations();

        return ResponseEntity.ok(educationNames);
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(){
        List<EmployeeDTO> employeeDTOS = employeeService.getAllEmployees();

        return ResponseEntity.ok(employeeDTOS);
    }

    @PostMapping()
    public ResponseEntity<EmployeeDTO> creatEmployee(
            @RequestBody AddEmployeeDTO addEmployeeDTO
            ){
       EmployeeDTO employeeDTO = employeeService.addEmployee(addEmployeeDTO);

      return   ResponseEntity.
                created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(employeeDTO.getId())
                                .toUri()
                ).body(employeeDTO);
    }

    @PostMapping("/edit")
    public ResponseEntity<EmployeeDTO> editEmployee(
            @RequestBody EmployeeDTO employeeDTO
    ){
        employeeService.editEmployee(employeeDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") long id){
        employeeService.removeEmployee(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findEmployeeById(@PathVariable("id") long id){

        return ResponseEntity.ok(employeeService.getEmployeeByID(id));
    }

}
