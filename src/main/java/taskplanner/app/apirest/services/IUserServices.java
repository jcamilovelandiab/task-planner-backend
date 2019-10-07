package taskplanner.app.apirest.services;

import org.springframework.stereotype.Service;
import taskplanner.app.apirest.entities.User;
import taskplanner.app.apirest.exception.TaskPlannerException;

import java.util.List;

@Service
public interface IUserServices {
    User createUser(User user) throws TaskPlannerException;
    User updateUser(User user) throws TaskPlannerException;
    void removeUser(String userId) throws TaskPlannerException;
    User getUserById(String userId) throws  TaskPlannerException;
    List<User> getUsers() throws  TaskPlannerException;

}
