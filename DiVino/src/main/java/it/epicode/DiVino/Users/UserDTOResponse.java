package it.epicode.DiVino.Users;

import it.epicode.DiVino.Enums.Role;
import lombok.Data;



    @Data
    public class UserDTOResponse {
        private Long id;
        private String username;
        private String firstName;
        private String lastName;
        private Role role;
    }


