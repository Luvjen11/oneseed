package oneseed.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @NotEmpty(message = "Username should not be null or empty")
    @Size(min = 3, message = "Username should have at least 3 characters")
    private String username;

    @NotEmpty(message = "Email should not be null or empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password should not be null or empty")
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;
}
