package bg.softuni.hrm_data.web;

import bg.softuni.hrm_data.model.dto.AddTaskDTO;
import bg.softuni.hrm_data.model.dto.TaskDTO;
import bg.softuni.hrm_data.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //get all tasks
    @GetMapping()
    public ResponseEntity<List<TaskDTO>> getAllTasks(){
        List<TaskDTO> taskDTOS = taskService.getAllTasksDTOS();

        return ResponseEntity.ok(taskDTOS);
    }

    //add new task
    @PostMapping()
    public ResponseEntity<Void> addTask(@RequestBody AddTaskDTO addTaskDTO
                                        ){
        taskService.addTask(addTaskDTO);

        return ResponseEntity.ok().build();
    }

    //get task by id
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> findTaskById(@PathVariable("id") long id){
        TaskDTO taskDTO = taskService.getTaskById(id);

        return ResponseEntity.ok(taskDTO);
    }

    //edit task
    @PostMapping("/edit")
    public ResponseEntity<TaskDTO> editTask(
            @RequestBody TaskDTO taskDTO
    ){
        taskService.editTask(taskDTO);

        return ResponseEntity.ok().build();
    }

    //delete task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id){
        taskService.removeTask(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
