package taskplanner.app.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import taskplanner.app.apirest.entities.Task;
import taskplanner.app.apirest.services.ITaskServices;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/tasks")
@CrossOrigin(value = "*")
public class TaskController {

    @Autowired
    @Qualifier("taskService")
    ITaskServices taskServices;

    @GetMapping
    public ResponseEntity<?> getTasks() {
        try {
            return new ResponseEntity<>(taskServices.getTasks(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable String taskId) {
        try {
            return new ResponseEntity<>(taskServices.getTaskById(taskId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        try {
            if(task.getResponsible()==null || task.getResponsible().getEmail()==null || task.getTitle()==null ||
               task.getDueDate()==null || task.getStatus()==null ){
                return new ResponseEntity<>("Please fill in title, due date, status, and responsible’s email", HttpStatus.BAD_REQUEST);
            }
            String uniqueID = UUID.randomUUID().toString();
            task.setId(uniqueID);
            Task t = taskServices.createTask(task);
            if(t==null) return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(t, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<?>  updateTask(@PathVariable String taskId, @RequestBody Task task) {
        try {
            Task t = taskServices.updateTask(task);
            if(t==null) return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>("OK",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?>  deleteTask(@PathVariable String taskId) {
        try {
            taskServices.removeTask(taskId);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


