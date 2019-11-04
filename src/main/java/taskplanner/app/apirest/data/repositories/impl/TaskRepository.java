package taskplanner.app.apirest.data.repositories.impl;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;
import taskplanner.app.apirest.data.entities.Task;

import java.util.List;


public interface TaskRepository extends MongoRepository<Task, String>{

    Task findBy_id(String id);

    @Query(value = "{ 'user' : { $eq : ?0} }")
    List<Task> findTasksByUserId(ObjectId _id);

}
