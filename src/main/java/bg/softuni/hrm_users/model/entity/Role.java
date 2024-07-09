package bg.softuni.hrm_users.model.entity;

import bg.softuni.hrm_users.model.enums.RoleName;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private RoleName roleName;
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<User> users;

    public Role() {
        this.users = new ArrayList<>();
    }

    public Role(RoleName roleName) {
        this();

        this.roleName = roleName;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
