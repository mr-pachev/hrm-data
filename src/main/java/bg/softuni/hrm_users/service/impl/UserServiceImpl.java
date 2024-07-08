package bg.softuni.hrm_users.service.impl;

import bg.softuni.hrm_users.model.dto.AddUserDTO;
import bg.softuni.hrm_users.model.dto.UserDTO;
import bg.softuni.hrm_users.model.entity.Employee;
import bg.softuni.hrm_users.model.entity.User;
import bg.softuni.hrm_users.model.enums.RoleName;
import bg.softuni.hrm_users.repository.EmployeeRepository;
import bg.softuni.hrm_users.repository.RoleRepository;
import bg.softuni.hrm_users.repository.UserRepository;
import bg.softuni.hrm_users.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(ModelMapper mapper, PasswordEncoder passwordEncoder, EmployeeRepository employeeRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean addUser(AddUserDTO addUserDTO) {
        User user = mapper.map(addUserDTO, User.class);
        Optional<User> isExistUser = userRepository.findByUsername(addUserDTO.getUsername());

        Optional<Employee> currentEmployee = employeeRepository.findAllByIdentificationNumber(addUserDTO.getIdentificationNumber());

        if (isExistUser.isPresent() || currentEmployee.isEmpty()) {
            return false;
        }

        user.setEmployee(currentEmployee.get());
        user.setRole(roleRepository.findByRoleName(RoleName.valueOf(addUserDTO.getRole())));
        user.setPassword(passwordEncoder.encode(addUserDTO.getPassword()));

        userRepository.save(user);

        return true;
    }

    @Override
    public UserDTO getUserById(long id) {
        return userRepository
                .findById(id)
                .map(UserServiceImpl::map)
                .orElseThrow(() -> new IllegalArgumentException("Not found!"));
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();

        for (User user : userRepository.findAll()) {
            users.add(map(user));
        }

        return users;
    }

    public static UserDTO map(User user){
       return new UserDTO(
               user.getId(),
               user.getEmployee().getIdentificationNumber(),
               user.getUsername(),
               user.getPassword(),
               user.getRole().getRoleName().name()
       );
    }
}
