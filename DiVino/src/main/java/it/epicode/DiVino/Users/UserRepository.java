package it.epicode.DiVino.Users;

import it.epicode.DiVino.Wineries.WineryResponsePrj;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    List<UserResponsePrj> findAllBy();
}

