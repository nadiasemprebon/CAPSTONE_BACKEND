package it.epicode.DiVino.Security;



import it.epicode.DiVino.Users.User;
import it.epicode.DiVino.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
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
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginModel.username(), loginModel.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtils.generateToken(authentication);
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



        userRepository.save(user);

        return new RegisteredUserDTO(user.getUserName());
    }

    public RegisteredUserDTO getUserDetails(String username) {
        User user = userRepository.findOneByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new RegisteredUserDTO(user.getUserName());
    }
}
