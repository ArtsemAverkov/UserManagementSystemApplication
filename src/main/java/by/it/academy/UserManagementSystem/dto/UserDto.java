package by.it.academy.UserManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 4, max = 30, message = "Username length must be between 4 and 30 characters")
    private String username;

    @NotBlank(message = "Surname cannot be blank")
    private String surname;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Role cannot be blank")
    @Pattern(regexp = "ADMINISTRATOR|USERSALE_USER|CUSTOMER_USER|SECURE_API_USER",
            message = "Role must be one of 'ADMINISTRATOR', 'USERSALE_USER', 'CUSTOMER_USER', or 'SECURE_API_USER'")
    private String role;

}
