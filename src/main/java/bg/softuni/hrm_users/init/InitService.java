package bg.softuni.hrm_users.init;

import bg.softuni.hrm_users.model.entity.Role;
import bg.softuni.hrm_users.model.enums.RoleName;
import bg.softuni.hrm_users.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InitService implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public InitService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        long countRole = this.roleRepository.count();

        if (countRole > 0) {
            return;
        }


        List<Role> toInsertRole = Arrays.stream(RoleName.values())
                .map(Role::new)
                .toList();

        this.roleRepository.saveAll(toInsertRole);
    }
}
