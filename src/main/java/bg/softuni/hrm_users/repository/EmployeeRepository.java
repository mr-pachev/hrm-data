package bg.softuni.hrm_users.repository;

import bg.softuni.hrm_users.model.entity.Department;
import bg.softuni.hrm_users.model.entity.Employee;
import bg.softuni.hrm_users.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByIdentificationNumber(String number);
    List<Employee> findAllByDepartment(Department department);
    Employee findByDepartment(Department department);
    Optional<Employee> findByFirstNameAndMiddleNameAndLastName(String firstName, String middleName, String lastName);
    @Query("SELECT e.projects FROM Employee e WHERE e.id = :employeeId")
    List<Project> findProjectsByEmployeeId(@Param("employeeId") Long employeeId);
}
