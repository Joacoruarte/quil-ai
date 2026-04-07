package redrock.rhino.quilai.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {

    @NotNull(message = "The username cannot be null")
    @NotBlank(message = "The username cannot be blank")
    private String username;

    @NotNull(message = "The password cannot be null")
    @NotBlank(message = "The password cannot be blank")
    private String password;

    @NotBlank
    @Email(message = "The email is not valid")
    private String email;
}
