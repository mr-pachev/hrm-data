package bg.softuni.hrm_users.repository;

import bg.softuni.hrm_users.model.entity.Role;
import bg.softuni.hrm_users.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(RoleName roleName);
}
