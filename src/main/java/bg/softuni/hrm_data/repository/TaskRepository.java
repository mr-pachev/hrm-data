package bg.softuni.hrm_data.repository;

import bg.softuni.hrm_data.model.entity.Employee;
import bg.softuni.hrm_data.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByEmployee(Employee employee);
}
