package taskplanner.app.apirest.services;

import org.springframework.stereotype.Service;
import taskplanner.app.apirest.data.entities.User;
import taskplanner.app.apirest.exception.TaskPlannerException;

import java.util.List;
import java.util.Optional;

@Service
public interface IUserServices {
    User createUser(User user) throws TaskPlannerException;
    void updateUser(User user) throws TaskPlannerException;
    void removeUser(String userId) throws TaskPlannerException;

    User getUserByEmail(String userEmail) throws TaskPlannerException;
    User getUserByUsername(String username) throws TaskPlannerException;
    User getUserById(String _id) throws TaskPlannerException;

    List<User> getUsers() throws TaskPlannerException;

}
