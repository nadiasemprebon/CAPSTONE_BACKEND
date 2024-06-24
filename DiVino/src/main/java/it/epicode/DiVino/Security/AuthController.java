package it.epicode.DiVino.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginModel loginModel) {
        String token = authService.login(loginModel);
        RegisteredUserDTO userDetails = authService.getUserDetails(loginModel.username()); // Aggiungi un metodo per ottenere i dettagli dell'utente
        return ResponseEntity.ok(new LoginResponseDTO(userDetails, token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDTO registerUserDTO) {
        RegisteredUserDTO registeredUser = authService.register(registerUserDTO);
        return ResponseEntity.ok(registeredUser);
    }
}
