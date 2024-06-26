
package it.epicode.DiVino.Security;


import it.epicode.DiVino.Users.User;
import it.epicode.DiVino.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userEntity = userRepository.findOneByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return SecurityUserDetails.build(userEntity);
    }
}
