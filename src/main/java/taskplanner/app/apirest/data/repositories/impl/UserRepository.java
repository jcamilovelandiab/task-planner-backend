package taskplanner.app.apirest.data.repositories.impl;

import taskplanner.app.apirest.data.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findBy_id(String _id);
    User findByEmail(String email);
    User findByUsername(String name);

}
