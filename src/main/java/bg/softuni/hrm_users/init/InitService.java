package bg.softuni.hrm_users.init;

import bg.softuni.hrm_users.model.entity.Education;
import bg.softuni.hrm_users.model.entity.Position;
import bg.softuni.hrm_users.model.enums.EducationName;
import bg.softuni.hrm_users.model.enums.PositionName;
import bg.softuni.hrm_users.repository.EducationRepository;
import bg.softuni.hrm_users.repository.PositionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InitService implements CommandLineRunner {
    private final PositionRepository positionRepository;
    private final EducationRepository educationRepository;
   Map<PositionName, String> descriptionPosition = new HashMap<>();

    public InitService(PositionRepository positionRepository,
                       EducationRepository educationRepository) {
        this.positionRepository = positionRepository;
        this.educationRepository = educationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initializePositionMap();

        long countPosition = this.positionRepository.count();
        long countEducation = this.educationRepository.count();

        if (countPosition > 0) {
            return;
        }
        if (countEducation > 0) {
            return;
        }

        List<Position> toInsertPosition = Arrays.stream(PositionName.values())
                .map(pos -> new Position(pos, descriptionPosition.get(pos)))
                .toList();

        List<Education> toInsertEducation = Arrays.stream(EducationName.values())
                .map(Education::new)
                .toList();

        this.positionRepository.saveAll(toInsertPosition);
        this.educationRepository.saveAll(toInsertEducation);
    }

    private void initializePositionMap() {
        descriptionPosition = new HashMap<>();
        descriptionPosition.put(PositionName.CHIEF_EXECUTIVE_OFFICER, "The CEO is responsible for the overall management and strategic direction of the company.");
        descriptionPosition.put(PositionName.CHIEF_OPERATING_OFFICER, "The COO oversees the day-to-day operations of the company. They ensure that the business is running efficiently and effectively, often managing departments like production, marketing, and human resources.");
        descriptionPosition.put(PositionName.CHIEF_FINANCIAL_OFFICER, "The CFO manages the financial actions of the company. They oversee financial planning, financial risk management, record-keeping, and financial reporting.");
        descriptionPosition.put(PositionName.FINANCIAL_OFFICER, "These duties help the financial officer to effectively manage the financial aspects and maintain the financial stability and sustainability of the business.");
        descriptionPosition.put(PositionName.HUMAN_RESOURCES_MANAGER, "The HR Manager handles all aspects of human resources within the company. They recruit and onboard new employees.");
        descriptionPosition.put(PositionName.HEAD_OF_DEPARTMENT, "The role of Head of Department is crucial in ensuring the effective functioning and success of their respective department within the broader organizational structure.");
        descriptionPosition.put(PositionName.IT_ADMINISTRATOR, "The IT Administrator manages the company's information technology infrastructure. They handle hardware and software installations, network security, system upgrades, and troubleshooting IT issues.");
        descriptionPosition.put(PositionName.PROJECT_MANAGER, "Project Managers play a critical role in driving project success by effectively managing resources, risks, and stakeholder expectations throughout the project lifecycle.");
        descriptionPosition.put(PositionName.MARKETING_MANAGER, "The Marketing Manager develops and implements marketing strategies to promote the company's products or services.");
        descriptionPosition.put(PositionName.DEVELOPER, "A developer is responsible for writing, testing, and maintaining code for software applications, websites, or other digital products.");
        descriptionPosition.put(PositionName.SENIOR_DEVELOPER, "This position typically involves significant technical expertise and responsibility within a software development team or organization.");
        descriptionPosition.put(PositionName.LEAD_DEVELOPER, "A Lead Developer is responsible for guiding and overseeing the technical aspects of software projects.");
        descriptionPosition.put(PositionName.QUALITY_ASSURANCE, "Quality Assurance (QA) refers to a systematic process or set of activities designed to ensure that products or services meet specified requirements and standards.");
        descriptionPosition.put(PositionName.CUSTOMER_SUPPORT, "Assist its customers in making cost-effective and correct use of a product or service.");
        descriptionPosition.put(PositionName.CLEANER, "Person responsible for maintaining the cleanliness and hygiene of the office environment. Lays a crucial role in creating a pleasant and healthy work environment for office employees.");
    }
}
