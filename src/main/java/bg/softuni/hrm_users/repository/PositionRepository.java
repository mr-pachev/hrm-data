package bg.softuni.hrm_users.repository;

import bg.softuni.hrm_users.model.entity.Employee;
import bg.softuni.hrm_users.model.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findByPositionName(String positionName);
}
