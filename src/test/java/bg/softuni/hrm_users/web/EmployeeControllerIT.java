package bg.softuni.hrm_users.web;

import bg.softuni.hrm_users.model.entity.Employee;
import bg.softuni.hrm_users.model.enums.EducationName;
import bg.softuni.hrm_users.repository.DepartmentRepository;
import bg.softuni.hrm_users.repository.EducationRepository;
import bg.softuni.hrm_users.repository.EmployeeRepository;
import bg.softuni.hrm_users.repository.PositionRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class EmployeeControllerIT {
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    public void testGetEmployeeByID() throws Exception {
        var actualEntity = createTestEmployee();

        ResultActions result = mockMvc
                .perform(get("/employees/{id}", actualEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(actualEntity.getId().intValue())))
                .andExpect(jsonPath("$.firstName", is(actualEntity.getFirstName())))
                .andExpect(jsonPath("$.middleName", is(actualEntity.getMiddleName())))
                .andExpect(jsonPath("$.lastName", is(actualEntity.getLastName())))
                .andExpect(jsonPath("$.identificationNumber", is(actualEntity.getIdentificationNumber())))
                .andExpect(jsonPath("$.startDate", is(actualEntity.getStartDate().toString())))
                .andExpect(jsonPath("$.position", is(actualEntity.getPosition().getPositionName())));
    }

    @Test
    public void testEmployeeNotFound() throws Exception {
        mockMvc
                .perform(get("/employees/{id}", "1000000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateEmployee() throws Exception {
        MvcResult result = mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                  {
                    "firstName": "John",
                    "middleName": "Michael",
                    "lastName": "Doe",
                    "identificationNumber": "123456789",
                    "age": 30,
                    "startDate": "2024-07-12",
                    "position": "DEVELOPER",
                    "department": "IT_DEPARTMENT",
                    "education": "HIGHER"
                  }
                """)
                ).andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn();

        String body = result.getResponse().getContentAsString();

        int id = JsonPath.read(body, "$.id");

        Optional<Employee> createdEmployeeOpt = employeeRepository.findById((long) id);

        Assertions.assertTrue(createdEmployeeOpt.isPresent());

        Employee createdEmployee = createdEmployeeOpt.get();

        Assertions.assertEquals("John", createdEmployee.getFirstName());
        Assertions.assertEquals("Michael", createdEmployee.getMiddleName());
        Assertions.assertEquals("Doe", createdEmployee.getLastName());
        Assertions.assertEquals("123456789", createdEmployee.getIdentificationNumber());
        Assertions.assertEquals(30, createdEmployee.getAge());
        Assertions.assertEquals("2024-07-12", createdEmployee.getStartDate().toString());
        Assertions.assertEquals("DEVELOPER", createdEmployee.getPosition().getPositionName());
        Assertions.assertEquals("IT_DEPARTMENT", createdEmployee.getDepartment().getDepartmentName());
        Assertions.assertEquals("HIGHER", createdEmployee.getEducation().getEducationName().name());
    }

    @Test
    public void testDeleteOffer() throws Exception {

        var actualEntity = createTestEmployee();

        mockMvc.perform(delete("/employees/{id}", actualEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());

        Assertions.assertTrue(
                employeeRepository.findById(actualEntity.getId()).isEmpty()
        );
    }

    private Employee createTestEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("aaaaa");
        employee.setMiddleName("bbbbb");
        employee.setLastName("ccccc");
        employee.setIdentificationNumber("1111111119");
        employee.setAge(40);
        employee.setStartDate(LocalDate.now());
        employee.setPosition(positionRepository.findByPositionName("CLEANER"));
        employee.setDepartment(departmentRepository.findByDepartmentName("MAINTENANCE_DEPARTMENT"));
        employee.setEducation(educationRepository.findByEducationName(EducationName.HIGHER));

        return employeeRepository.save(employee);
    }
}
