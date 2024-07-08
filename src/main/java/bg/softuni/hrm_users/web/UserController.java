package bg.softuni.hrm_users.web;

import bg.softuni.hrm_users.model.dto.AddUserDTO;
import bg.softuni.hrm_users.model.dto.UserDTO;
import bg.softuni.hrm_users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserDTO> creatUser(
            @RequestBody AddUserDTO addUserDTO
            ){
        userService.addUser(addUserDTO);
            return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> userDTOS = userService.getAllUsers();
        return ResponseEntity.ok(userDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable("id") long id){

        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);

        return ResponseEntity.ok().build();
    }
}
