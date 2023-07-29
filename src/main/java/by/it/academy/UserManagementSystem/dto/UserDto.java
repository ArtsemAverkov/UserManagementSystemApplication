package by.it.academy.UserManagementSystem.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;
    private String surname;
    private String email;
    private String role;
}
