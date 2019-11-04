package taskplanner.app.apirest.services;

import org.springframework.stereotype.Service;
import taskplanner.app.apirest.data.entities.Task;
import taskplanner.app.apirest.data.entities.User;
import taskplanner.app.apirest.exception.TaskPlannerException;

import java.util.List;
import java.util.Optional;

@Service
public interface ITaskServices {
    Task createTask(Task task) throws TaskPlannerException;
    void updateTask(Task task) throws TaskPlannerException;
    void removeTask(String taskId) throws TaskPlannerException;
    Optional<Task> getTaskById(String taskId) throws TaskPlannerException;

    List<Task> getTasks() throws  TaskPlannerException;
    List<Task> getTasksByUserId(String userId) throws TaskPlannerException;
}
