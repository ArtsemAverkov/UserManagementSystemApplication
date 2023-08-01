package by.it.academy.UserManagementSystem.common.utill;

import by.it.academy.UserManagementSystem.dto.UserDto;
import by.it.academy.UserManagementSystem.entity.Role;
import by.it.academy.UserManagementSystem.entity.User;
import org.jetbrains.annotations.NotNull;

public class UserBuild {

    public static User buildUser(UserDto userDto){
        return User.builder()
                .id(RequestId.VALUE_1.getValue())
                .username(userDto.getUsername())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .role(Role.valueOf(userDto.getRole()))
                .build();
    }
//
//    public static User buildUserWithId(UserRequestProtos.UserRequestDto userDto){
//        return User.builder()
//                .id(RequestId.VALUE_1.getValue())
//                .username(userDto.getUsername())
//                .password(userDto.getPassword())
//                .role(new Role(userDto.getRole()))
//                .build();
//    }
//
    @NotNull
    public static String getContent(UserDto userDto) {
        return "{\n" +
                "  \"username\": \"" + userDto.getUsername() + "\",\n" +
                "  \"surname\": \"" + userDto.getSurname() + "\",\n" +
                "  \"email\": \"" + userDto.getEmail() + "\",\n" +
                "  \"role\": \"" + userDto.getRole() + "\"\n" +
                "}";
    }
//
//    @NotNull
//    public static Role getRole() {
//        Role role = new Role();
//        role.setId(1L);
//        role.setName("JOURNALIST");
//        role.setUsers(null);
//        return role;
//    }
}
