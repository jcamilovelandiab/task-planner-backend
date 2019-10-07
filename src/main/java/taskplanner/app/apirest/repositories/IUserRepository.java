package taskplanner.app.apirest.repositories;

import org.springframework.stereotype.Repository;
import taskplanner.app.apirest.entities.Login;
import taskplanner.app.apirest.entities.User;
import taskplanner.app.apirest.exception.TaskPlannerException;

@Repository
public interface IUserRepository extends DAO<User, String> {

    boolean login(Login login) throws TaskPlannerException;

}
