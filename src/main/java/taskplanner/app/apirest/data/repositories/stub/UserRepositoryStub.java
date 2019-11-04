package taskplanner.app.apirest.data.repositories.stub;

import taskplanner.app.apirest.data.entities.User;
import taskplanner.app.apirest.exception.TaskPlannerException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*
@Component
@Qualifier("userRepositoryStub")
public class UserRepositoryStub implements IUserRepository {

    Map<String, User> users = new HashMap<String, User>();

    @Override
    public List<User> findAll() throws TaskPlannerException {
        return new ArrayList<User>(users.values());
    }

    @Override
    public User find(String findByPK) throws TaskPlannerException {
        if (!users.containsKey(findByPK)) {
            throw new TaskPlannerException("This user does not exist");
        }
        return users.get(findByPK);
    }

    @Override
    public User save(User entity) throws TaskPlannerException {
        if (users.containsKey(entity.getUsername())) {
            throw new TaskPlannerException("This username has already been taken");
        }
        users.put(entity.getUsername(), entity);
        return users.get(entity.getUsername());
    }

    @Override
    public User update(User entity) throws TaskPlannerException {
        if (!users.containsKey(entity.getUsername())) {
            throw new TaskPlannerException("This user does not exist");
        }
        User user = users.get(entity.getUsername());
        user = entity;
        users.remove(user.getUsername());
        users.put(user.getUsername(), user);
        return user;
    }

    @Override
    public void delete(User entity) throws TaskPlannerException {
        users.remove(entity.getUsername());
    }

    @Override
    public void remove(String pkEntity) throws TaskPlannerException {
        users.remove(pkEntity);
    }

    @Override
    public User findByEmail(String userEmail) throws TaskPlannerException {
        User user = null;
        for (Map.Entry<String, User> entry : users.entrySet()) {
            User u = entry.getValue();
            if (u.getEmail().equals(userEmail)) {
                user = u;
                break;
            }
        }
        if (user == null) {
            throw new TaskPlannerException("This user does not exist");
        }
        return user;
    }

}
*/