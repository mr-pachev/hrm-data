package bg.softuni.hrm_users.service.impl;

import bg.softuni.hrm_users.model.dto.AddUserDTO;
import bg.softuni.hrm_users.model.dto.UserDTO;
import bg.softuni.hrm_users.model.entity.User;
import bg.softuni.hrm_users.model.enums.RoleName;
import bg.softuni.hrm_users.repository.RoleRepository;
import bg.softuni.hrm_users.repository.UserRepository;
import bg.softuni.hrm_users.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(ModelMapper mapper, PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public void addUser(AddUserDTO addUserDTO) {
        User user = mapper.map(addUserDTO, User.class);

        user.setRole(roleRepository.findByRoleName(RoleName.valueOf(addUserDTO.getRole())));
        user.setPassword(passwordEncoder.encode(addUserDTO.getPassword()));

        userRepository.save(user);
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

    @Override
    public void edithUser(UserDTO userDTO) {
        User user = reMapUser(userDTO);

        userRepository.save(user);
    }

    public static UserDTO map(User user){
       return new UserDTO(
               user.getId(),
               user.getUsername(),
               user.getPassword(),
               user.getIdentificationNumber(),
               user.getRole().getRoleName().name()
       );
    }
    public User reMapUser(UserDTO userDTO){
        User user = userRepository.findByUsername(userDTO.getUsername()).get();

        user.setUsername(userDTO.getUsername());
        user.setRole(roleRepository.findByRoleName(RoleName.valueOf(userDTO.getRole())));

        return user;
    }
}
