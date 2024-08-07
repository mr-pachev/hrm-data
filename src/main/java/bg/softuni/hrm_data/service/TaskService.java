package bg.softuni.hrm_data.service;

import bg.softuni.hrm_data.model.dto.AddTaskDTO;
import bg.softuni.hrm_data.model.dto.TaskDTO;

import java.util.List;

public interface TaskService {
    //get all tasks
    List<TaskDTO> getAllTasksDTOS();
    //add new task
    void addTask(AddTaskDTO addTaskDTO);
    //get task by id
   TaskDTO getTaskById(long id);

    //edit task
    void editTask(TaskDTO taskDTO);

    //delete task
    void removeTask(long id);
}
