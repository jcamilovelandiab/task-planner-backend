package taskplanner.app.apirest.repositories;

import org.springframework.stereotype.Repository;
import taskplanner.app.apirest.entities.Task;
import taskplanner.app.apirest.entities.User;
import taskplanner.app.apirest.exception.TaskPlannerException;

import java.util.List;

@Repository
public interface ITaskRepository extends DAO<Task, String> {

    List<Task> findTasksByUserId(String userId) throws TaskPlannerException;
    Task assignTaskToUser(String taskId, User user) throws TaskPlannerException;

}
