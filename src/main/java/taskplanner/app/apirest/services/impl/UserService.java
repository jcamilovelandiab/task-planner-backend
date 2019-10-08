package taskplanner.app.apirest.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import taskplanner.app.apirest.entities.User;
import taskplanner.app.apirest.exception.TaskPlannerException;
import taskplanner.app.apirest.repositories.IUserRepository;
import taskplanner.app.apirest.services.IUserServices;

import java.util.List;

@Component("userService")
public class UserService implements IUserServices {

    @Autowired
    @Qualifier("userRepositoryStub")
    private IUserRepository userRepository;

    @Override
    public User createUser(User user) throws TaskPlannerException {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) throws TaskPlannerException {
        return userRepository.update(user);
    }

    @Override
    public void removeUser(String userId) throws TaskPlannerException {
        userRepository.remove(userId);
    }

    @Override
    public User getUserById(String userId) throws TaskPlannerException {
        return userRepository.find(userId);
    }

    @Override
    public List<User> getUsers() throws TaskPlannerException {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String userEmail) throws TaskPlannerException {
        return userRepository.findByEmail(userEmail);
    }

}
