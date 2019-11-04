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

    @GetMapping("/{username}/tasks")
    public ResponseEntity<?> getTasksByUsername(@PathVariable String username) {
        try {
            User user = userServices.getUserByUsername(username);
            return new ResponseEntity<>(taskServices.getTasksByUserId(user.get_id().toString()), HttpStatus.CREATED);
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

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody User user) {
        try {
            user.setUsername(username);
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
