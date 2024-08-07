package bg.softuni.hrm_data.web;

import bg.softuni.hrm_data.model.dto.AddDepartmentDTO;
import bg.softuni.hrm_data.model.dto.DepartmentDTO;
import bg.softuni.hrm_data.model.dto.EmployeeDTO;
import bg.softuni.hrm_data.model.dto.EmployeeNameDTO;
import bg.softuni.hrm_data.service.DepartmentService;
import bg.softuni.hrm_data.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    private final EmployeeService employeeService;

    public DepartmentController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    //get all departments
    @GetMapping()
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
        List<DepartmentDTO> departmentDTOS = departmentService.getAllDepartments();

        return ResponseEntity.ok(departmentDTOS);
    }

    //add new department
    @PostMapping()
    public ResponseEntity<Void> createDepartment(
            @RequestBody AddDepartmentDTO addDepartmentDTO
    ){
        departmentService.addDepartment(addDepartmentDTO);

        return ResponseEntity.ok().build();
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

    //get all employees names
    @GetMapping("/all-employees")
    public ResponseEntity<List<EmployeeNameDTO>> getAllEmployees(){
        List<EmployeeNameDTO> employees = employeeService.allEmployeesNames();

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
                                            @RequestBody EmployeeNameDTO employeeNameDTO
    ){

        departmentService.addEmployee(employeeNameDTO, idDep);

        return ResponseEntity.ok().build();
    }

    //delete current employee from current department
    @DeleteMapping("/employee/{idEm}/{idDep}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("idEm") Long idEm,
                                               @PathVariable("idDep")Long idDep){

        departmentService.removeEmployee(idEm, idDep);

        return ResponseEntity
                .noContent()
                .build();
    }
}
