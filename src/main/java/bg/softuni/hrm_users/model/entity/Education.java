package bg.softuni.hrm_users.model.entity;

import bg.softuni.hrm_users.model.enums.EducationName;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "educations")
public class Education extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private EducationName educationName;

    @OneToMany(mappedBy = "education")
    private List<Employee> employees;

    public Education() {
        this.employees = new ArrayList<>();
    }

    public Education(EducationName educationName) {
        this();

        this.educationName = educationName;
    }

    public EducationName getEducationName() {
        return educationName;
    }

    public void setEducationName(EducationName educationName) {
        this.educationName = educationName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
