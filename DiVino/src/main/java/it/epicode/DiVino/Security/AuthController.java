package it.epicode.DiVino.Security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")

    public ResponseEntity<?> login(@RequestBody LoginModel loginModel) {
        log.info("Received login request with username: {}", loginModel.userName());
        log.info("Received login request with password: {}", loginModel.password());

        if (loginModel.userName() == null || loginModel.password() == null) {
            log.error("Username or password is null in the request body");
            return ResponseEntity.badRequest().body("Username or password cannot be null");
        }

        String token = authService.login(loginModel);
        RegisteredUserDTO userDetails = authService.getUserDetails(loginModel.userName());
        return ResponseEntity.ok(new LoginResponseDTO(userDetails, token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDTO registerUserDTO) {
        RegisteredUserDTO registeredUser = authService.register(registerUserDTO);
        return ResponseEntity.ok(registeredUser);
    }
}
