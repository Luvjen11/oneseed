package oneseed.backend.controller;

import oneseed.backend.model.User;
import oneseed.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oneseed")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Register a new user
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            // Check if username already exists
            if (userService.usernameExists(user.getUsername())) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Username already exists");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            // Check if email already exists
            if (userService.emailExists(user.getEmail())) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Email already exists");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            // Encode password
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            // Create user
            User createdUser = userService.createUser(user);

            // Return user without password
            createdUser.setPassword(null);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User registered successfully");
            response.put("user", createdUser);
            
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Registration failed: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Login endpoint (basic auth will handle authentication)
     */
    @PostMapping("/login")
    public ResponseEntity<?> login() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get current user info
     */
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        // This would typically get the current user from SecurityContext
        // For now, return a placeholder
        Map<String, String> response = new HashMap<>();
        response.put("message", "Current user info would be returned here");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
