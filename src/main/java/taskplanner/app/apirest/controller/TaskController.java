package taskplanner.app.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import taskplanner.app.apirest.entities.Task;
import taskplanner.app.apirest.services.ITaskServices;

@RestController
@RequestMapping(value = "v1/tasks")
@CrossOrigin(value = "*")
public class TaskController {
    
    @Autowired
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
            Task t = taskServices.createTask(task);
            if(t==null) return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>("OK", HttpStatus.CREATED);
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


