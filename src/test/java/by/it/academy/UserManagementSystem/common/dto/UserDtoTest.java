package by.it.academy.UserManagementSystem.common.dto;

import by.it.academy.UserManagementSystem.dto.UserDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserDtoTest {
    @Test
    public void testBuilder() {
        UserDto user = UserDto.builder()
                .username("john_doe")
                .surname("Doe")
                .email("john.doe@example.com")
                .role("user")
                .build();

        assertEquals("john_doe", user.getUsername());
        assertEquals("Doe", user.getSurname());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("user", user.getRole());
    }

    @Test
    public void testSettersAndGetters() {
        UserDto user = new UserDto();

        user.setUsername("jane_doe");
        user.setSurname("Doe");
        user.setEmail("jane.doe@example.com");
        user.setRole("admin");

        assertEquals("jane_doe", user.getUsername());
        assertEquals("Doe", user.getSurname());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals("admin", user.getRole());
    }

    @Test
    public void testAllArgsConstructor() {
        UserDto user = new UserDto("jane_doe", "Doe", "jane.doe@example.com", "admin");

        assertEquals("jane_doe", user.getUsername());
        assertEquals("Doe", user.getSurname());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals("admin", user.getRole());
    }

    @Test
    public void testNoArgsConstructor() {
        UserDto user = new UserDto();

        assertNull(user.getUsername());
        assertNull(user.getSurname());
        assertNull(user.getEmail());
        assertNull(user.getRole());
    }
}
