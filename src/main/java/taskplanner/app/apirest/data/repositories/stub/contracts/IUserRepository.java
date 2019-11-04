package taskplanner.app.apirest.data.repositories;

import org.springframework.stereotype.Repository;
import taskplanner.app.apirest.data.entities.User;
import taskplanner.app.apirest.exception.TaskPlannerException;

@Repository
public interface IUserRepository extends DAO<User, String> {

    User findByEmail(String userEmail) throws TaskPlannerException;

}
