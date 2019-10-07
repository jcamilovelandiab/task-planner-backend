package taskplanner.app.apirest.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import taskplanner.app.apirest.entities.Task;
import taskplanner.app.apirest.entities.User;
import taskplanner.app.apirest.exception.TaskPlannerException;
import taskplanner.app.apirest.repositories.ITaskRepository;
import taskplanner.app.apirest.services.ITaskServices;

import java.util.List;

@Component
public class TaskService  implements ITaskServices {

    @Autowired
    @Qualifier("taskRepositoryStub")
    private ITaskRepository taskRepository;

    @Override
    public Task createTask(Task task) throws TaskPlannerException {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) throws TaskPlannerException {
        return taskRepository.update(task);
    }

    @Override
    public void removeTask(String taskId) throws TaskPlannerException {
        taskRepository.remove(taskId);
    }

    @Override
    public Task getTaskById(String taskId) throws TaskPlannerException {
        return taskRepository.find(taskId);
    }

    @Override
    public List<Task> getTasks() throws TaskPlannerException {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksByUserId(String userId) throws TaskPlannerException {
        return taskRepository.findTasksByUserId(userId);
    }

    @Override
    public Task assignTaskToUser(String taskId, User user) throws TaskPlannerException {
        return taskRepository.assignTaskToUser(taskId, user);
    }
}
