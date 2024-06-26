package it.epicode.DiVino.Security;

import it.epicode.DiVino.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class RegisterUserDTO {
    String userName;
    String password;
    String firstName;
    String lastName;
    String email;


}
