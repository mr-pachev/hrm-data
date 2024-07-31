package bg.softuni.hrm_users.service.impl;

import bg.softuni.hrm_users.model.dto.AddTaskDTO;
import bg.softuni.hrm_users.model.dto.TaskDTO;
import bg.softuni.hrm_users.model.entity.Employee;
import bg.softuni.hrm_users.model.entity.Task;
import bg.softuni.hrm_users.repository.EmployeeRepository;
import bg.softuni.hrm_users.repository.TaskRepository;
import bg.softuni.hrm_users.service.TaskService;
import bg.softuni.hrm_users.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;


    public TaskServiceImpl(TaskRepository taskRepository, EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    //get all tasks
    @Override
    public List<TaskDTO> getAllTasksDTOS() {
        List<Task> allTasks = taskRepository.findAll();

        return getTaskDTOS(allTasks);
    }

    //add new task
    @Override
    public void addTask(AddTaskDTO addTaskDTO) {
        Task task = mapper.map(addTaskDTO, Task.class);
        Employee employee = findEmployeeByFullName(addTaskDTO.getEmployeeName());

        task.setEmployee(employee);
        taskRepository.save(task);
    }

    //get task by id
    @Override
    public TaskDTO getTaskById(long id) {
        Task task = taskRepository.findById(id).orElseThrow(ObjectNotFoundException::new);

        return mapToTaskDTO(task);
    }

    //edit task
    @Override
    public void editTask(TaskDTO taskDTO) {
        Task task = taskRepository.findById(taskDTO.getId()).orElseThrow(ObjectNotFoundException::new);
        Employee employee = findEmployeeByFullName(taskDTO.getEmployeeName());

        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());

        LocalDate startDate = mapper.map(taskDTO.getStartDate(), LocalDate.class);
        task.setStartDate(startDate);

        LocalDate endDate = mapper.map(taskDTO.getEndDate(), LocalDate.class);
        task.setEndDate(endDate);

        task.setEmployee(employee);
        taskRepository.save(task);
    }

    //delete task
    @Override
    public void removeTask(long id) {
        taskRepository.deleteById(id);
    }

    private List<TaskDTO> getTaskDTOS(List<Task> allTasks) {
        List<TaskDTO> taskDTOS = new ArrayList<>();

        for (Task task : allTasks) {
            TaskDTO taskDTO = mapToTaskDTO(task);
            taskDTOS.add(taskDTO);
        }

        return taskDTOS;
    }

    private TaskDTO mapToTaskDTO(Task task){
        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStartDate(task.getStartDate().toString());
        taskDTO.setEndDate(task.getEndDate().toString());
        taskDTO.setEmployeeName(task.getEmployee().toString());

        return taskDTO;
    }

    private Employee findEmployeeByFullName(String fullName){
        String firstName = fullName.split(" ")[0];
        String middleName = fullName.split(" ")[1];
        String lastName = fullName.split(" ")[2];

        return employeeRepository.findByFirstNameAndMiddleNameAndLastName(firstName, middleName, lastName).orElseThrow(ObjectNotFoundException::new);
    }
}
