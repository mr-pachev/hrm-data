package bg.softuni.hrm_users.repository;

import bg.softuni.hrm_users.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findAllByIdentificationNumber(String IdentificationNumber);
}
