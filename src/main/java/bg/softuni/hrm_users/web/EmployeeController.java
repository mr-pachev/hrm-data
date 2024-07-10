package bg.softuni.hrm_users.web;

import bg.softuni.hrm_users.model.dto.EmployeeDTO;
import bg.softuni.hrm_users.service.DepartmentService;
import bg.softuni.hrm_users.service.EducationService;
import bg.softuni.hrm_users.service.EmployeeService;
import bg.softuni.hrm_users.service.PositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
       List<String> departmentNames = departmentService.getAllDepartments();

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
            @RequestBody EmployeeDTO employeeDTO
    ){
        employeeService.addEmployee(employeeDTO);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/edith")
    public ResponseEntity<EmployeeDTO> edithEmployee(
            @RequestBody EmployeeDTO employeeDTO
    ){
        employeeService.edithEmployee(employeeDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id){
        employeeService.removeEmployee(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findEmployeeById(@PathVariable("id") long id){

        return ResponseEntity.ok(employeeService.getEmployeeByID(id));
    }

}