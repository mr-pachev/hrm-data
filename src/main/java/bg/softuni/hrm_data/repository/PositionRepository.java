package bg.softuni.hrm_data.repository;

import bg.softuni.hrm_data.model.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findByPositionName(String positionName);
}
