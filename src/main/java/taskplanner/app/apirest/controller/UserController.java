package taskplanner.app.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import taskplanner.app.apirest.entities.Login;
import taskplanner.app.apirest.entities.User;
import taskplanner.app.apirest.services.IUserServices;

@RestController
@RequestMapping(value = "v1/users")
@CrossOrigin(value = "*")
public class UserController {
    @Autowired
    IUserServices userServices;

    @PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Login login){
		try {
			if(userServices.login(login)){
                return new ResponseEntity<>("ERROR", HttpStatus.NOT_ACCEPTABLE);
            }
			return new ResponseEntity<>("OK",HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @GetMapping
    public ResponseEntity<?> getUsers() {
        try {
            return new ResponseEntity<>(userServices.getUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getTaskById(@PathVariable String userId) {
        try {
            return new ResponseEntity<>(userServices.getUserById(userId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User u = userServices.createUser(user);
            if(u==null) return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>("OK", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?>  updateUser(@PathVariable String userId, @RequestBody User user) {
        try {
            System.out.println("LLEGO AL PUT");
            User u = userServices.updateUser(user);
            if(u==null) return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>("OK",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?>  deleteUser(@PathVariable String userId) {
        try {
            userServices.removeUser(userId);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
