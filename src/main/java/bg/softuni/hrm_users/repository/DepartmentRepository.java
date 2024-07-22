package bg.softuni.hrm_users.repository;

import bg.softuni.hrm_users.model.entity.Department;
import bg.softuni.hrm_users.model.entity.Employee;
import bg.softuni.hrm_users.model.entity.Project;
import bg.softuni.hrm_users.model.enums.DepartmentName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByDepartmentName(DepartmentName departmentName);
    boolean existsByDepartmentName(DepartmentName departmentName);
}
