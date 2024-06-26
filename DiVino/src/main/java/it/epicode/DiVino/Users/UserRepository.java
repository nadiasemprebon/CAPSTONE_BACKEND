package it.epicode.DiVino.Users;

import it.epicode.DiVino.Wineries.WineryResponsePrj;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    List<UserResponsePrj> findAllBy();
    Optional<User> findOneByUserName(String username);
    boolean existsByUserName(String userName);
}

