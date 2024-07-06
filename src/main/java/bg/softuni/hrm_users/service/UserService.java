package bg.softuni.hrm_users.service;


import bg.softuni.hrm_users.model.dto.AddUserDTO;

public interface UserService {
    boolean addUser(AddUserDTO addUserDTO);
}
