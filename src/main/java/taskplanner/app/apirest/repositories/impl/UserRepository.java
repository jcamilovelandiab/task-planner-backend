package taskplanner.app.apirest.repositories.impl;

import taskplanner.app.apirest.entities.User;
import taskplanner.app.apirest.exception.TaskPlannerException;
import taskplanner.app.apirest.repositories.IUserRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("userRepository")
public class UserRepository implements IUserRepository {
    @Override
    public List<User> findAll() throws TaskPlannerException {
        return null;
    }

    @Override
    public User find(String findByPK) throws TaskPlannerException {
        return null;
    }

    @Override
    public User save(User entity) throws TaskPlannerException {
        return null;
    }

    @Override
    public User update(User entity) throws TaskPlannerException {
        return null;
    }

    @Override
    public void delete(User entity) throws TaskPlannerException {

    }

    @Override
    public void remove(String pkEntity) throws TaskPlannerException {

    }
}
