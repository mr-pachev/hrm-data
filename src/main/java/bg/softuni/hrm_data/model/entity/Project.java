package bg.softuni.hrm_data.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity{
    @NotBlank
    private String name;
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String description;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endData;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department responsibleDepartment;

    @ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
    private List<Employee> employees;

    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.getProjects().remove(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndData() {
        return endData;
    }

    public void setEndData(LocalDate endData) {
        this.endData = endData;
    }

    public Department getResponsibleDepartment() {
        return responsibleDepartment;
    }

    public void setResponsibleDepartment(Department responsibleDepartment) {
        this.responsibleDepartment = responsibleDepartment;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
