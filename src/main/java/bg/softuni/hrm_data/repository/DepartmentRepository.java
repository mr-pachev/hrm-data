package bg.softuni.hrm_data.repository;

import bg.softuni.hrm_data.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByDepartmentName(String departmentName);
    Department findById(long id);
    boolean existsByDepartmentName(String departmentName);
}
