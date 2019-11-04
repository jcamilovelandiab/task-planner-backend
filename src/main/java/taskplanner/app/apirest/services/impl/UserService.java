package taskplanner.app.apirest.services.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import taskplanner.app.apirest.data.entities.User;
import taskplanner.app.apirest.data.repositories.impl.UserRepository;
import taskplanner.app.apirest.exception.TaskPlannerException;
import taskplanner.app.apirest.services.IUserServices;

import java.util.List;

@Component("userService")
public class UserService implements IUserServices {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) throws TaskPlannerException {
        user.set_id(ObjectId.get());
        userRepository.save(user);
        return userRepository.findBy_id(user.get_id().toString());
    }

    @Override
    public void updateUser(User user) throws TaskPlannerException {
        userRepository.save(user);
    }

    @Override
    public void removeUser(String userId) throws TaskPlannerException {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getUsers() throws TaskPlannerException {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String userEmail) throws TaskPlannerException {
        return userRepository.findByEmail(userEmail);
    }

    @Override
    public User getUserByUsername(String username) throws TaskPlannerException {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserById(String _id) throws TaskPlannerException {
        return userRepository.findBy_id(_id);
    }

}
