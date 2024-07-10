package bg.softuni.hrm_users.service.impl;

import bg.softuni.hrm_users.repository.DepartmentRepository;
import bg.softuni.hrm_users.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<String> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(department -> department.getDepartmentName().name())
                .collect(Collectors.toList());
    }
}
