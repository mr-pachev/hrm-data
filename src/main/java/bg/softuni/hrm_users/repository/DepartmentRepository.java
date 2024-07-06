package bg.softuni.hrm_users.repository;

import bg.softuni.hrm_users.model.entity.Department;
import bg.softuni.hrm_users.model.enums.DepartmentName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByDepartmentName(DepartmentName departmentName);
}
