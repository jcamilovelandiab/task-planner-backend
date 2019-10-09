package taskplanner.app.apirest.controller;

import javax.servlet.ServletException;

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
    public Token login(@RequestBody User login) throws ServletException {
        String jwtToken = "";
        if (login.getEmail() == null || login.getPassword() == null) {
            throw new ServletException("Please fill in email and password");
        }
        User user = null;
        try {
            user = userServices.getUserByEmail(login.getEmail());
        } catch (TaskPlannerException e) {
            throw new ServletException("Email not found.");
        }
        assert (user != null);
        if (!user.getPassword().equals(login.getPassword())) {
            throw new ServletException("Invalid login. Please check your email and password.");
        }
        jwtToken = Jwts.builder().setSubject(login.getEmail()).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        return new Token(jwtToken);
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
            if(user.getEmail()==null || user.getUsername()==null || user.getFullName()==null || user.getPassword()==null){
                return new ResponseEntity<>("Invalid user creation. Please fill in username, fullName, email, and password.", HttpStatus.BAD_REQUEST);
            }
            User u = userServices.createUser(user);
            if (u == null)
                return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>("OK", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody User user) {
        try {
            System.out.println("LLEGO AL PUT");
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

    public class Token {
        String accessToken;

        public Token(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String access_token) {
            this.accessToken = access_token;
        }
    }

}
