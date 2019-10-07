package taskplanner.app.apirest.repositories.impl;

import taskplanner.app.apirest.entities.Task;
import taskplanner.app.apirest.entities.User;
import taskplanner.app.apirest.exception.TaskPlannerException;
import taskplanner.app.apirest.repositories.ITaskRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("taskRepository")
public class TaskRepository implements ITaskRepository {


    @Override
    public List<Task> findTasksByUserId(String userId) throws TaskPlannerException {
        return null;
    }

    @Override
    public Task assignTaskToUser(String taskId, User user) throws TaskPlannerException {
        return null;
    }

    @Override
    public List<Task> findAll() throws TaskPlannerException {
        return null;
    }

    @Override
    public Task find(String findByPK) throws TaskPlannerException {
        return null;
    }

    @Override
    public Task save(Task entity) throws TaskPlannerException {
        return null;
    }

    @Override
    public Task update(Task entity) throws TaskPlannerException {
        return null;
    }

    @Override
    public void delete(Task entity) throws TaskPlannerException {

    }

    @Override
    public void remove(String pkEntity) throws TaskPlannerException {

    }
}
