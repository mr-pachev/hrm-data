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

    //get all department employees
    List<EmployeeDTO> allDepartmentEmployees(long id);

    //get all employees names
    List<DepartmentEmployeeDTO> allEmployeesNames();

    //add current employee in current department
    void addEmployee(DepartmentEmployeeDTO departmentEmployeeDTO, long idDep);

    //delete employee in current department
    void removeEmployee(long idEm, long idDep);
}
