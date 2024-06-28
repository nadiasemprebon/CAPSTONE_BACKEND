package it.epicode.DiVino.Security;



import it.epicode.DiVino.Enums.Role;
import it.epicode.DiVino.Users.User;
import it.epicode.DiVino.Users.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public String login(LoginModel loginModel) {
        log.info("Attempting to authenticate user: {}", loginModel.userName());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginModel.userName(), loginModel.password()));
        log.info("Authentication successful for user: {}", loginModel.userName());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateToken(authentication);
        log.info("Generated JWT token for user: {}", loginModel.userName());
        return token;
    }

    public RegisteredUserDTO register(RegisterUserDTO registerUserDTO) {
        if (userRepository.existsByUserName(registerUserDTO.getUserName())) {
            throw new RuntimeException("Username is already taken!");
        }

        User user = new User();
        user.setUserName(registerUserDTO.getUserName());
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        user.setFirstName(registerUserDTO.getFirstName());
        user.setLastName(registerUserDTO.getLastName());
        user.setEmail(registerUserDTO.getUserName());
        user.setRole(Role.valueOf("USER"));


        userRepository.save(user);

        return new RegisteredUserDTO(user.getId(), user.getUserName(), user.getFirstName(), user.getLastName(),user.getEmail());
    }

    public RegisteredUserDTO getUserDetails(String username) {
        User user = userRepository.findOneByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new RegisteredUserDTO(user.getId(), user.getUserName(), user.getFirstName(), user.getLastName(),user.getEmail());
    }
}
