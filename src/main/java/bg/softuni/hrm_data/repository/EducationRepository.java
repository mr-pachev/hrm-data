package bg.softuni.hrm_data.repository;

import bg.softuni.hrm_data.model.entity.Education;
import bg.softuni.hrm_data.model.enums.EducationName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    Education findByEducationName(EducationName educationName);
}
