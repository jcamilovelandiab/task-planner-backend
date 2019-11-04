package taskplanner.app.apirest.data.repositories;

import org.springframework.stereotype.Repository;
import taskplanner.app.apirest.data.entities.Task;
import taskplanner.app.apirest.data.entities.User;
import taskplanner.app.apirest.exception.TaskPlannerException;

import java.util.List;

@Repository
public interface ITaskRepository extends DAO<Task, String> {

    List<Task> findTasksByUserId(String userId) throws TaskPlannerException;
    Task assignTaskToUser(String taskId, User user) throws TaskPlannerException;

}
