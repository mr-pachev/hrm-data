package bg.softuni.hrm_users.web;

import bg.softuni.hrm_users.model.dto.*;
import bg.softuni.hrm_users.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    //get all projects
    @GetMapping()
    public ResponseEntity<List<ProjectDTO>> getAllProjects(){
        List<ProjectDTO> projectDTOS = projectService.getAllProjectsDTOS();

        return ResponseEntity.ok(projectDTOS);
    }


    //get project by id
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> findProjectById(@PathVariable("id") long id){
        ProjectDTO projectDTO = projectService.getProjectById(id);

        return ResponseEntity.ok(projectDTO);
    }

    //get employees names from current project
    @GetMapping("/all-employees")
    public ResponseEntity<List<ProjectEmployeeDTO>> getAllEmployees(){
        List<ProjectEmployeeDTO> employees = projectService.allEmployeesNames();

        return ResponseEntity.ok(employees);
    }

    //get employees from current project
    @GetMapping("/employees/{id}")
    public ResponseEntity<List<EmployeeDTO>> getAllProjectEmployees(@PathVariable("id") long id){
        List<EmployeeDTO> employeeDTOS = projectService.allProjectEmployees(id);

        return ResponseEntity.ok(employeeDTOS);
    }

    //creat project
    @PostMapping()
    public ResponseEntity<Void> createProject(
            @RequestBody AddProjectDTO addProjectDTO
    ){
        projectService.creatProject(addProjectDTO);

        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .build()
                                .toUri()
                )
                .build();
    }

    //edit project
    @PostMapping("/edit")
    public ResponseEntity<ProjectDTO> editProject(
            @RequestBody ProjectDTO projectDTO
    ){
        projectService.editProject(projectDTO);

        return ResponseEntity.ok().build();
    }

    //add employee in current project
    @PostMapping("/add-employee/{idPr}")
    public ResponseEntity<Void> addEmployee(@PathVariable("idPr") Long idPr,
                                            @RequestBody ProjectEmployeeDTO projectEmployeeDTO
    ){
        projectService.addEmployee(projectEmployeeDTO, idPr);

        return ResponseEntity.ok().build();
    }

    //delete current employee from current project
    @DeleteMapping("/employee/{idEm}/{idPr}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("idEm") Long idEm,
                                               @PathVariable("idPr")Long idPr){
        projectService.removeEmployee(idEm, idPr);

        return ResponseEntity
                .noContent()
                .build();
    }

    //delete project
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable("id") Long id){
        projectService.removeProject(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
