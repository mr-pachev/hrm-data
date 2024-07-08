package bg.softuni.hrm_users.web;

import bg.softuni.hrm_users.model.dto.AddEmployeeDTO;
import bg.softuni.hrm_users.model.dto.EmployeeDTO;
import bg.softuni.hrm_users.model.entity.Employee;
import bg.softuni.hrm_users.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping()
    public ResponseEntity<AddEmployeeDTO> creatEmployee(
            @RequestBody AddEmployeeDTO addEmployeeDTO
    ){
        employeeService.addEmployee(addEmployeeDTO);
        return ResponseEntity.ok().build();
    }

//    @GetMapping()
//    public ResponseEntity<List<EmployeeDTO>> getAllUsers(){
//        List<EmployeeDTO> employeeDTOS = userService.getAllUsers();
//
//        return ResponseEntity.ok(employeeDTOS);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserDTO> findUserById(@PathVariable("id") long id){
//
//        return ResponseEntity.ok(userService.getUserById(id));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") long id){
//        userService.deleteUser(id);
//
//        return ResponseEntity.ok().build();
//    }

}
