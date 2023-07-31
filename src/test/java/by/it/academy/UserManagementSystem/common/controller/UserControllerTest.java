package by.it.academy.UserManagementSystem.common.controller;

import by.it.academy.UserManagementSystem.UserManagementSystemApplication;
import by.it.academy.UserManagementSystem.common.extension.ValidParameterResolverUser;
import by.it.academy.UserManagementSystem.common.utill.RequestId;
import by.it.academy.UserManagementSystem.controller.UserController;
import by.it.academy.UserManagementSystem.dto.UserDto;
import by.it.academy.UserManagementSystem.entity.User;
import by.it.academy.UserManagementSystem.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static by.it.academy.UserManagementSystem.common.utill.UserBuild.buildUser;
import static by.it.academy.UserManagementSystem.common.utill.UserBuild.getContent;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class contains test cases to validate the UserController using JUnit 5 and Spring's MockMvc.
 * The class is annotated with @WebMvcTest(UserController.class) to only test the UserController
 * while relying on the Spring TestContext Framework.
 *
 * <p>The class uses @MockBean to create a mock instance of the UserService to isolate the controller from the service layer.
 * It also includes @Autowired MockMvc to perform HTTP requests and validate responses.
 *
 * <p>The class defines five test methods:
 * - create: Tests the create method of UserController to verify the successful creation of a user.
 * - read: Tests the read method of UserController to verify the retrieval of a user by ID.
 * - delete: Tests the delete method of UserController to verify the successful deletion of a user.
 * - update: Tests the update method of UserController to verify the successful update of a user.
 * - readAll: Tests the readAll method of UserController to verify the retrieval of all users with pagination.
 */

@ContextConfiguration(classes = UserManagementSystemApplication.class)
@WebMvcTest(UserController.class)
@ExtendWith(ValidParameterResolverUser.class)
@AutoConfigureMockMvc
@DisplayName("Testing User Controller")
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test the create method of UserController to verify the successful creation of a user.
     *
     * @param userDto The UserDto object containing user information for the test.
     * @throws Exception if an exception occurs during the test.
     */
    @Test
    public void create(UserDto userDto) throws Exception {
        when(userService.create(any(UserDto.class))).thenReturn(RequestId.VALUE_1.getValue());
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getContent(userDto)))
                .andExpect(status().isCreated())
                .andExpect(content().string(String.valueOf(RequestId.VALUE_1.getValue())));
        verify(userService).create(any(UserDto.class));
    }

    /**
     * Test the read method of UserController to verify the retrieval of a user by ID.
     *
     * @param userDto The UserDto object containing user information for the test.
     * @throws Exception if an exception occurs during the test.
     */

    @Test
    public void read(UserDto userDto) throws Exception {
        User user = buildUser(userDto);
        when(userService.read(RequestId.VALUE_1.getValue())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", RequestId.VALUE_1.getValue()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is(user.getUsername())));
        verify(userService).read(RequestId.VALUE_1.getValue());
    }


    /**
     * Test the delete method of UserController to verify the successful deletion of a user.
     *
     * @param userDto The UserDto object containing user information for the test.
     * @throws Exception if an exception occurs during the test.
     */
    @Test
    public void delete(UserDto userDto) throws Exception {
        when(userService.delete(RequestId.VALUE_1.getValue())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", RequestId.VALUE_1.getValue()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        verify(userService).delete(RequestId.VALUE_1.getValue());
    }

    /**
     * Test the update method of UserController to verify the successful update of a user.
     *
     * @param userDto The UserDto object containing user information for the test.
     * @throws Exception if an exception occurs during the test.
     */

    @Test
    public void update(UserDto userDto) throws Exception {
        when(userService.update(userDto, RequestId.VALUE_1.getValue())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.patch("/users/{id}", RequestId.VALUE_1.getValue())
                .contentType(MediaType.APPLICATION_JSON)
                .content(getContent(userDto)))
                .andExpect(status().isOk());
        verify(userService).update(any(UserDto.class), any());
    }

    /**
     * Test the readAll method of UserController to verify the retrieval of all users with pagination.
     *
     * @param userDto The UserDto object containing user information for the test.
     * @throws Exception if an exception occurs during the test.
     */

    @Test
    public void readAll(UserDto userDto) throws Exception {
        User user = buildUser(userDto);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userService.readAll(Pageable.ofSize(10).withPage(0))).thenReturn(userList);
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0]username", Matchers.is(user.getUsername())));
        verify(userService).readAll(Pageable.ofSize(10).withPage(0));
    }
}
