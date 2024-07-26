package bg.softuni.hrm_users.service;

import bg.softuni.hrm_users.model.dto.*;

import java.util.List;

public interface DepartmentService {
    //department
    List<String> getAllDepartmentNames();
    List<DepartmentDTO> getAllDepartments();
    void addDepartment(AddDepartmentDTO addDepartmentDTO);
    boolean isExistDepartment(String name);
    DepartmentDTO getDepartmentByID(long id);
    void editDepartment(DepartmentDTO departmentDTO);
    void removeDepartment(long id);

    //department employees
    List<EmployeeDTO> allDepartmentEmployees(long id);
    List<DepartmentEmployeeDTO> allEmployeesNames();
    void addEmployee(DepartmentEmployeeDTO departmentEmployeeDTO, long idDep);
}
