package taskplanner.app.apirest.repositories;

import org.springframework.stereotype.Repository;
import taskplanner.app.apirest.entities.User;
import taskplanner.app.apirest.exception.TaskPlannerException;

@Repository
public interface IUserRepository extends DAO<User, String> {

    User findByEmail(String userEmail) throws TaskPlannerException;

}
