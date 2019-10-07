package taskplanner.app.apirest.repositories.stub;

import taskplanner.app.apirest.entities.Task;
import taskplanner.app.apirest.entities.User;
import taskplanner.app.apirest.exception.TaskPlannerException;
import taskplanner.app.apirest.repositories.ITaskRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("taskRepositoryStub")
public class TaskRepositoryStub implements ITaskRepository {

    Map<String, Task> tasks = new HashMap<String, Task>();

    @Override
    public List<Task> findAll() throws TaskPlannerException {
        return new ArrayList<Task>(tasks.values());
    }

    @Override
    public Task find(String findByPK) throws TaskPlannerException {
        if(!tasks.containsKey(findByPK)){
            throw new TaskPlannerException("This task does not exist.");
        }
        return tasks.get(findByPK);
    }

    @Override
    public List<Task> findTasksByUserId(String userId) throws TaskPlannerException {
        List<Task> userTasks = new ArrayList<Task>();
        tasks.forEach((taskId, task)->{
            if(task.getResponsible().getId().equals(userId)){
                userTasks.add(task);
            }
        });
        return userTasks;
    }

    @Override
    public Task assignTaskToUser(String taskId, User user) throws TaskPlannerException {
        if(!tasks.containsKey(taskId)){
            throw new TaskPlannerException("This task does not exist.");
        }
        Task task = tasks.get(taskId);
        task.setResponsible(user);
        return task;
    }

    @Override
    public Task save(Task entity) throws TaskPlannerException {
        if(tasks.containsKey(entity.getId())){
            throw new TaskPlannerException("This task already exists");
        }
        tasks.put(entity.getId(), entity);
        return tasks.get(entity.getId());
    }

    @Override
    public Task update(Task entity) throws TaskPlannerException {
        if(!tasks.containsKey(entity.getId())){
            throw new TaskPlannerException("This task does not exist.");
        }
        
        Task task = tasks.get(entity.getId());
        task=entity;
        tasks.remove(task.getId());
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public void delete(Task entity) throws TaskPlannerException {
        tasks.remove(entity.getId());
    }

    @Override
    public void remove(String pkEntity) throws TaskPlannerException {
        tasks.remove(pkEntity);
    }
}
