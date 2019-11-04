package taskplanner.app.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskplanner.app.apirest.data.entities.User;
import taskplanner.app.apirest.services.ITaskServices;
import taskplanner.app.apirest.services.IUserServices;

@RestController
@RequestMapping(value = "api/users")
@CrossOrigin(value = "*")
public class UserController {

    @Autowired
    @Qualifier("taskService")
    private ITaskServices taskServices;

    @Autowired
    @Qualifier("userService")
    private IUserServices userServices;

    @GetMapping("/{userId}/tasks")
    public ResponseEntity<?> getTasksByUsername(@PathVariable String userId) {
        try {
            User user = userServices.getUserById(userId);
            return new ResponseEntity<>(taskServices.getTasksByResponsibleId(userId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        try {
            User user = userServices.getUserById(userId);
            if(user==null){
                return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        try {
            userServices.updateUser(user);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        try {
            userServices.removeUser(username);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
