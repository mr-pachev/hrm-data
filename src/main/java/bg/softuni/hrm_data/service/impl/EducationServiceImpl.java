package bg.softuni.hrm_data.service.impl;

import bg.softuni.hrm_data.repository.EducationRepository;
import bg.softuni.hrm_data.service.EducationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;

    public EducationServiceImpl(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    //get all educations names
    @Override
    public List<String> getAllEducations() {
        return educationRepository.findAll().stream()
                .map(education -> education.getEducationName().name())
                .collect(Collectors.toList());
    }
}
