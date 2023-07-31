package by.it.academy.UserManagementSystem.common.controller;

import by.it.academy.UserManagementSystem.UserManagementSystemApplication;
import by.it.academy.UserManagementSystem.common.extension.ValidParameterResolverUser;
import by.it.academy.UserManagementSystem.common.utill.RequestId;
import by.it.academy.UserManagementSystem.controller.UserController;
import by.it.academy.UserManagementSystem.dto.UserDto;
import by.it.academy.UserManagementSystem.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.NoSuchElementException;
import static by.it.academy.UserManagementSystem.common.utill.UserBuild.getContent;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * This class contains test cases to validate the exception handling in the UserController.
 * It uses JUnit 5 for testing and Spring's MockMvc to simulate HTTP requests and responses.
 * The class is annotated with @WebMvcTest(UserController.class) to only test the UserController
 * while relying on the Spring TestContext Framework.
 *
 * <p>The class uses @MockBean to create a mock instance of the UserService to isolate the controller from the service layer.
 * It also includes @Autowired MockMvc to perform HTTP requests and validate responses.
 *
 * <p>The class defines three test methods:
 * - handleHttpRequestMethodNotSupported: Tests the handling of HttpRequestMethodNotSupportedException
 *   when an unsupported HTTP method is used.
 * - noSuchElement: Tests the handling of NoSuchElementException when attempting to read a user that does not exist.
 * - serverErrorRuntime: Tests the handling of RuntimeException when an unexpected runtime error occurs.
 */

@ContextConfiguration(classes = UserManagementSystemApplication.class)
@WebMvcTest(UserController.class)
@ExtendWith(ValidParameterResolverUser.class)
@AutoConfigureMockMvc
@DisplayName("Testing ExceptionHandler Controller")
public class ControllerExceptionHandlerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test the handling of HttpRequestMethodNotSupportedException when an unsupported HTTP method is used.
     *
     * @param userDto The UserDto object containing user information for the test.
     * @throws Exception if an exception occurs during the test.
     */

    @Test
    public void handleHttpRequestMethodNotSupported (UserDto userDto) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getContent(userDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"message\":\"METHOD NOT ALLOWED\",\"errorData\"" +
                        ":\"org.springframework.web.HttpRequestMethodNotSupportedException:" +
                        " Request method 'POST' is not supported\"}"));
    }

    /**
     * Test the handling of NoSuchElementException when attempting to read a user that does not exist.
     *
     * @throws Exception if an exception occurs during the test.
     */

    @Test
    public void noSuchElement() throws Exception {
        when(userService.read(RequestId.VALUE_1.getValue())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", RequestId.VALUE_1.getValue()))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("{\"message\":\"NO SUCH ELEMENT\",\"errorData\":" +
                        "\"java.util.NoSuchElementException\"}"));
    }

    /**
     * Test the handling of RuntimeException when an unexpected runtime error occurs.
     *
     * @throws Exception if an exception occurs during the test.
     */

    @Test
    public void serverErrorRuntime() throws Exception {
        when(userService.read(RequestId.VALUE_1.getValue())).thenThrow(RuntimeException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", RequestId.VALUE_1.getValue()))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("{\"message\":\"INCORRECT REQUEST\",\"errorData\":" +
                        "\"java.lang.RuntimeException\"}"));
    }
}
