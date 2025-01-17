package bg.softuni.hrm_data.model;

import bg.softuni.hrm_data.model.dto.AddDepartmentDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddDepartmentDTOTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void setup() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    public static void teardown() {
        validatorFactory.close();
    }

    @Test
    public void testValidAddDepartmentDTO() {
        AddDepartmentDTO dto = new AddDepartmentDTO();
        dto.setDepartmentName("IT_DEPARTMENT");
        dto.setManager("Munio Munev");
        dto.setDescriptions("Human Resources Department");

        Set<ConstraintViolation<AddDepartmentDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidDepartmentName() {
        AddDepartmentDTO dto = new AddDepartmentDTO();
        dto.setDepartmentName("HR"); //too short
        dto.setManager("Petio Marinkin");
        dto.setDescriptions("Human Resources Department");

        Set<ConstraintViolation<AddDepartmentDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size(), "There should be one validation error");
        assertTrue(violations.iterator().next().getMessage().contains("size must be between 3 and 50"));
    }

    @Test
    public void testInvalidManager() {
        AddDepartmentDTO dto = new AddDepartmentDTO();
        dto.setDepartmentName("IT_DEPARTMENT");
        dto.setManager(""); //blank manager
        dto.setDescriptions("Human Resources Department");

        Set<ConstraintViolation<AddDepartmentDTO>> violations = validator.validate(dto);

        //Print all error messages
        for (ConstraintViolation<AddDepartmentDTO> violation : violations) {
            System.out.println(violation.getMessage());
        }

        assertEquals(2, violations.size(), "There should be two validation error");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("must not be blank")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("size must be between 9 and 50")));
    }

    @Test
    public void testInvalidDescriptions() {
        AddDepartmentDTO dto = new AddDepartmentDTO();
        dto.setDepartmentName("Human Resources");
        dto.setManager("Zdravko Jeliazkov");
        dto.setDescriptions("Short"); //too short

        Set<ConstraintViolation<AddDepartmentDTO>> violations = validator.validate(dto);
        assertEquals(1, violations.size(), "There should be one validation error");
        assertTrue(violations.iterator().next().getMessage().contains("size must be between 10 and 255"));
    }

    @Test
    void testGettersAndSetters() {

        AddDepartmentDTO department = new AddDepartmentDTO();

        department.setDepartmentName("Human Resources");
        assertEquals("Human Resources", department.getDepartmentName());

        department.setManager("Ivan Ivanov");
        assertEquals("Ivan Ivanov", department.getManager());

        department.setDescriptions("This department handles all HR related tasks and employee welfare.");
        assertEquals("This department handles all HR related tasks and employee welfare.", department.getDescriptions());
    }
}
