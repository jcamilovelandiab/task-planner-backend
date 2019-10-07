package taskplanner.app.apirest.repositories.stub;

import taskplanner.app.apirest.entities.Login;
import taskplanner.app.apirest.entities.User;
import taskplanner.app.apirest.exception.TaskPlannerException;
import taskplanner.app.apirest.repositories.IUserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("userRepositoryStub")
public class UserRepositoryStub implements IUserRepository {

    Map<String, User> users = new HashMap<String, User>();;

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
        if (users.containsKey(entity.getId())) {
            throw new TaskPlannerException("This user already exists");
        }
        users.put(entity.getId(), entity);
        return users.get(entity.getId());
    }

    @Override
    public User update(User entity) throws TaskPlannerException {
        if (!users.containsKey(entity.getId())) {
            throw new TaskPlannerException("This user does not exist");
        }
        User user = users.get(entity.getId());
        user = entity;
        users.remove(user.getId());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public void delete(User entity) throws TaskPlannerException {
        users.remove(entity.getId());
    }

    @Override
    public void remove(String pkEntity) throws TaskPlannerException {
        users.remove(pkEntity);
    }

    @Override
    public boolean login(Login login) throws TaskPlannerException {
        
        boolean isRegistered = false;
        for (Map.Entry<String, User> entry : users.entrySet()) {
            User user = entry.getValue();
            if(user.getEmail().equals(login.getEmail()) && 
                user.getPassword().equals(login.getPassword())){
                    isRegistered = true; break;
            }
        }
        
        return isRegistered;
    }
}
