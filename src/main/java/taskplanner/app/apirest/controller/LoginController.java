package taskplanner.app.apirest.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskplanner.app.apirest.data.entities.User;
import taskplanner.app.apirest.exception.TaskPlannerException;
import taskplanner.app.apirest.services.IUserServices;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
@RequestMapping(value = "users")
@CrossOrigin(value = "*")
public class LoginController {

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
                user.getUsername(), user.getEmail(), user.get_id().toString()), HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            if(user.getEmail()==null || user.getUsername()==null || user.getFullName()==null || user.getPassword()==null) {
                return new ResponseEntity<>("Invalid user creation. Please fill in username, fullName, email, and password.", HttpStatus.BAD_REQUEST);
            }
            try{
                User u = userServices.getUserByEmail(user.getEmail());
                if(u!=null) return new ResponseEntity<>("This email is already associated with an account!", HttpStatus.CONFLICT);
                u = userServices.getUserByUsername(user.getUsername());
                if(u!=null) return new ResponseEntity<>("This username has already been taken!", HttpStatus.CONFLICT);
            }catch (Exception e){
            }
            User u = userServices.createUser(user);

            if (u == null)  return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);

            String jwtToken = Jwts.builder().setSubject(u.getEmail()).claim("roles", "user").setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
            return new ResponseEntity<>(new Token(jwtToken, user.getFullName().split(" ")[0],
                    user.getUsername(), user.getEmail(), user.get_id().toString()), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
        String id;
    }

}
