package SimpleStore.controller;

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
    public ResponseEntity<Integer> signupUser(@RequestBody UserRequest user) throws SQLException {
        int result = users.checkSignup(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<Integer> loginUser(@RequestBody UserRequest user) throws SQLException {
        int result = users.checkLogin(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(result);
    }

    public static class UserRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

}