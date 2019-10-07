package taskplanner.app.apirest.services;

import org.springframework.stereotype.Service;
import taskplanner.app.apirest.entities.Task;
import taskplanner.app.apirest.entities.User;
import taskplanner.app.apirest.exception.TaskPlannerException;

import java.util.List;

@Service
public interface ITaskServices {
    Task createTask(Task task) throws TaskPlannerException;
    Task updateTask(Task task) throws TaskPlannerException;
    void removeTask(String taskId) throws TaskPlannerException;
    Task getTaskById(String taskId) throws TaskPlannerException;

    List<Task> getTasks() throws  TaskPlannerException;
    List<Task> getTasksByUserId(String userId) throws TaskPlannerException;
    Task assignTaskToUser(String taskId, User user) throws TaskPlannerException;
}
