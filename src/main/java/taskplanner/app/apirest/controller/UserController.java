package taskplanner.app.apirest.controller;

import javax.servlet.ServletException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jersey.JerseyProperties.Servlet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskplanner.app.apirest.entities.User;
import taskplanner.app.apirest.exception.TaskPlannerException;
import taskplanner.app.apirest.services.IUserServices;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

@RestController
@RequestMapping(value = "v1/users")
@CrossOrigin(value = "*")
public class UserController {

    @Autowired
    @Qualifier("userService")
    IUserServices userServices;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User login) throws ServletException {
        String jwtToken = "";
        if ((login.getUsername()==null && login.getEmail() == null) || login.getPassword() == null) {
            return new ResponseEntity<>("Please fill in email or username, and password", HttpStatus.BAD_REQUEST);
        }
        User user = null;
        if(login.getEmail()!=null){
            try {
                user = userServices.getUserByEmail(login.getEmail());
            } catch (TaskPlannerException e) {
                return new ResponseEntity<>("Invalid login. Please check your email or username, and password.", HttpStatus.BAD_REQUEST);
            }
        }else if(login.getUsername()!=null){
            try{
                user = userServices.getUserByUsername(login.getUsername());
            } catch (TaskPlannerException e) {
                return new ResponseEntity<>("Invalid login. Please check your email or username, and password.", HttpStatus.BAD_REQUEST);
            }
        }
        if (!user.getPassword().equals(login.getPassword())) {
            return new ResponseEntity<>("Invalid login. Please check your email or username, and password.", HttpStatus.BAD_REQUEST);
        }
        jwtToken = Jwts.builder().setSubject(login.getEmail()).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        return new ResponseEntity<>( new Token(jwtToken, user.getFullName().split(" ")[0],
                    user.getUsername(), user.getEmail()), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        try {
            return new ResponseEntity<>(userServices.getUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getTasksByUsername(@PathVariable String username) {
        try {
            return new ResponseEntity<>(userServices.getUserByUsername(username), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            if(user.getEmail()==null || user.getUsername()==null || user.getFullName()==null || user.getPassword()==null) {
                return new ResponseEntity<>("Invalid user creation. Please fill in username, fullName, email, and password.", HttpStatus.BAD_REQUEST);
            }
            try{
                User u = userServices.getUserByEmail(user.getEmail());
                if(u!=null) return new ResponseEntity<>("This email is already associated with an account", HttpStatus.BAD_REQUEST);
            }catch (Exception e){
            }
            User u = userServices.createUser(user);
            if (u == null)  return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
            String jwtToken = Jwts.builder().setSubject(u.getEmail()).claim("roles", "user").setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
            return new ResponseEntity<>(new Token(jwtToken, user.getFullName().split(" ")[0],
                    user.getUsername(), user.getEmail()), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody User user) {
        try {
            user.setUsername(username);
            User u = userServices.updateUser(user);
            if (u == null)
                return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
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

    @Getter
    @Setter
    @AllArgsConstructor
    public class Token {
        String accessToken;
        String fullName;
        String username;
        String email;
    }

}
