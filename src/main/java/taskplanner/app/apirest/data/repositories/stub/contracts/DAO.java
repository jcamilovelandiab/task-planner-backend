package taskplanner.app.apirest.data.repositories;

import java.io.Serializable;
import java.util.List;

import taskplanner.app.apirest.exception.TaskPlannerException;

public interface DAO<T extends Serializable, PK>{

    public List<T> findAll() throws TaskPlannerException;
    public T find(PK findByPK) throws TaskPlannerException;
    public T save(T entity) throws TaskPlannerException;
    public T update(T entity) throws TaskPlannerException;
    public void delete(T entity) throws TaskPlannerException;
    public void remove(PK pkEntity) throws TaskPlannerException;

}
