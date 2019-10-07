package taskplanner.app.apirest.repositories;

import org.springframework.stereotype.Repository;
import taskplanner.app.apirest.entities.User;

@Repository
public interface IUserRepository extends DAO<User, String> {



}
