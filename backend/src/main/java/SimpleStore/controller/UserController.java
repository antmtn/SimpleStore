package SimpleStore.controller;

import SimpleStore.model.User;
import SimpleStore.relations.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final Users users = new Users();

    @PostMapping("/signup")
    public ResponseEntity<Integer> signupUser(@RequestBody User user) throws SQLException {
        int result = users.checkSignup(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<Integer> loginUser(@RequestBody User user) throws SQLException {
        int result = users.checkLogin(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(result);
    }


}