package oneseed.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotEmpty(message = "Username or email should not be null or empty")
    private String usernameOrEmail;
    
    @NotEmpty(message = "Password should not be null or empty")
    private String password;
}
