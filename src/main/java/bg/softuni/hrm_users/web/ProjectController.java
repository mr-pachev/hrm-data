package bg.softuni.hrm_users.web;

import bg.softuni.hrm_users.model.dto.AddDepartmentDTO;
import bg.softuni.hrm_users.model.dto.EmployeeDTO;
import bg.softuni.hrm_users.model.dto.ProjectDTO;
import bg.softuni.hrm_users.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @GetMapping()
    public ResponseEntity<List<ProjectDTO>> getAllProjects(){
        List<ProjectDTO> projectDTOS = projectService.getAllProjectsDTOS();

        return ResponseEntity.ok(projectDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> findProjectById(@PathVariable("id") long id){
        ProjectDTO projectDTO = projectService.getProjectById(id);

        return ResponseEntity.ok(projectDTO);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<List<EmployeeDTO>> getAllProjectEmployees(@PathVariable("id") long id){
        List<EmployeeDTO> employeeDTOS = projectService.allProjectEmployees(id);

        return ResponseEntity.ok(employeeDTOS);
    }

    @PostMapping("/edit")
    public ResponseEntity<ProjectDTO> editProject(
            @RequestBody ProjectDTO projectDTO
    ){
        projectService.editProject(projectDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/employee{idEm}/{idPr}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("idEm") Long idEm, @PathVariable("idPr")Long idPr){
        projectService.removeEmployee(idEm, idPr);

        return ResponseEntity
                .noContent()
                .build();
    }
}
