package AutoCareHub.controller;

import AutoCareHub.entity.User;
import AutoCareHub.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")

@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://autocarehub12.netlify.app"
})

public class AuthController {
    private final UserRepository userRepository;
    public AuthController(UserRepository userRepository) { this.userRepository = userRepository; }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (user.getName() == null || user.getEmail() == null || user.getPassword() == null || user.getPassword().length() < 4)
            return ResponseEntity.badRequest().body(Map.of("message", "Name, email and a password of at least 4 characters are required."));
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "An account with this email already exists."));
        User saved = userRepository.save(user);
        saved.setPassword(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User existing = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (existing == null || existing.getPassword() == null || !existing.getPassword().equals(user.getPassword()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid email or password."));
        existing.setPassword(null);
        return ResponseEntity.ok(existing);
    }
}
