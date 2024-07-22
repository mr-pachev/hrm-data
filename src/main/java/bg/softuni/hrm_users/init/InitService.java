package bg.softuni.hrm_users.init;

import bg.softuni.hrm_users.model.entity.Education;
import bg.softuni.hrm_users.model.enums.EducationName;
import bg.softuni.hrm_users.repository.EducationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class InitService implements CommandLineRunner {
    private final EducationRepository educationRepository;


    public InitService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        long countEducation = this.educationRepository.count();

        if (countEducation > 0) {
            return;
        }

        List<Education> toInsertEducation = Arrays.stream(EducationName.values())
                .map(Education::new)
                .toList();

        this.educationRepository.saveAll(toInsertEducation);
    }

}
