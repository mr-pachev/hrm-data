package bg.softuni.hrm_users.service;

import bg.softuni.hrm_users.model.dto.*;

import java.util.List;

public interface DepartmentService {
    //get all departments names
    List<String> getAllDepartmentNames();

    //get all departments
    List<DepartmentDTO> getAllDepartments();

    //checking is exist department by name
    boolean isExistDepartment(String name);

    //add new department
    void addDepartment(AddDepartmentDTO addDepartmentDTO);

    //get department by id
    DepartmentDTO getDepartmentByID(long id);

    //edit department
    void editDepartment(DepartmentDTO departmentDTO);

    //delete department
    void removeDepartment(long id);

    //get all employees from current department
    List<EmployeeDTO> allDepartmentEmployees(long id);

    //add current employee in current department
    void addEmployee(EmployeeNameDTO employeeNameDTO, long idDep);

    //delete employee from current department
    void removeEmployee(long idEm, long idDep);
}
