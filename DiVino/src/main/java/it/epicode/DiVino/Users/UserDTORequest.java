package it.epicode.DiVino.Users;

import it.epicode.DiVino.Enums.Role;
import lombok.Data;

    @Data
    public class UserDTORequest {
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private Role role;
    }


