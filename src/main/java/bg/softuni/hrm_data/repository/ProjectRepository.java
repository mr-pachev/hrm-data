package bg.softuni.hrm_data.repository;

import bg.softuni.hrm_data.model.entity.Department;
import bg.softuni.hrm_data.model.entity.Employee;
import bg.softuni.hrm_data.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByResponsibleDepartment(Department department);
    Optional<Project> findById(long id);

    @Query("SELECT p.employees FROM Project p WHERE p.id = :projectId")
    List<Employee> findEmployeesByProjectId(@Param("projectId") Long projectId);

}
