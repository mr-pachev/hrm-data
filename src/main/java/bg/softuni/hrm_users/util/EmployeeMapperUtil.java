package bg.softuni.hrm_users.util;

import bg.softuni.hrm_users.model.dto.EmployeeDTO;
import bg.softuni.hrm_users.model.entity.Employee;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMapperUtil {
    private static final ModelMapper mapper = new ModelMapper();

    public static List<EmployeeDTO> mapToEmployeeDTOS(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);
            employeeDTO.setPosition(employee.getPosition().getPositionName());
            employeeDTO.setDepartment(employee.getDepartment().getDepartmentName());
            employeeDTO.setEducation(employee.getEducation().getEducationName().name());

            employeeDTOS.add(employeeDTO);
        }

        return employeeDTOS;
    }
}
