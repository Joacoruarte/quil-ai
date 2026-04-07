package redrock.rhino.quilai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    @NotNull(message = "The username cannot be null")
    @NotBlank(message = "The username cannot be blank")
    private String username;

    @NotNull(message = "The password cannot be null")
    @NotBlank(message = "The password cannot be blank")
    private String password;
}