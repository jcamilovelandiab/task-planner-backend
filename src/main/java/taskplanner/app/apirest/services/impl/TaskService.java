package taskplanner.app.apirest.services.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import taskplanner.app.apirest.data.entities.Task;
import taskplanner.app.apirest.data.entities.User;
import taskplanner.app.apirest.exception.TaskPlannerException;
import taskplanner.app.apirest.data.repositories.impl.TaskRepository;;
import taskplanner.app.apirest.services.ITaskServices;

import java.util.List;
import java.util.Optional;

@Component("taskService")
public class TaskService  implements ITaskServices {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) throws TaskPlannerException {
        task.set_id(ObjectId.get());
        taskRepository.save(task);
        return taskRepository.findBy_id(task.getStrId());
    }

    @Override
    public void updateTask(Task task) throws TaskPlannerException {
        taskRepository.save(task);
    }

    @Override
    public void removeTask(String taskId) throws TaskPlannerException {
        taskRepository.deleteById(taskId);
    }

    @Override
    public Optional<Task> getTaskById(String taskId) throws TaskPlannerException {
        return taskRepository.findById(taskId);
    }

    @Override
    public List<Task> getTasks() throws TaskPlannerException {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksByUserId(String user_id) throws TaskPlannerException {
        return taskRepository.findTasksByUserId(new ObjectId(user_id));
    }

}
