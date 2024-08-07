package bg.softuni.hrm_data.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "positions")
public class Position extends BaseEntity{

    @NotBlank
    @Column(name = "position_name")
    private String positionName;
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "position", fetch = FetchType.EAGER)
    private List<Employee> employees;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
