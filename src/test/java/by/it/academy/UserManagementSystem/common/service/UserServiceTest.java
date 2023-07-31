package by.it.academy.UserManagementSystem.common.service;

import by.it.academy.UserManagementSystem.common.extension.ValidParameterResolverUser;
import by.it.academy.UserManagementSystem.common.utill.RequestId;
import by.it.academy.UserManagementSystem.dto.UserDto;
import by.it.academy.UserManagementSystem.entity.User;
import by.it.academy.UserManagementSystem.repository.UserRepository;
import by.it.academy.UserManagementSystem.service.UserApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.it.academy.UserManagementSystem.common.utill.UserBuild.buildUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 * This class contains test cases to validate the UserApiService using JUnit 5 and Mockito.
 * The class is nested with two inner classes, ValidData and InvalidData, which group related test scenarios.
 *
 * <p>The ValidData inner class contains test methods to verify the behavior of UserApiService with valid input data.
 * It uses @Mock and @InjectMocks annotations to create a mock instance of UserRepository and inject it into UserApiService.
 *
 * <p>The InvalidData inner class contains test methods to verify the behavior of UserApiService with invalid input data.
 *
 * <p>ValidData Inner Class:
 * - shouldReadUserWhenUserValid: Tests the read method of UserApiService to verify the successful retrieval of a user by ID.
 * - shouldCreateUserWhenUserValid: Tests the create method of UserApiService to verify the successful creation of a user.
 * - shouldDeleteUserWhenUserValid: Tests the delete method of UserApiService to verify the successful deletion of a user.
 * - shouldUpdateUserWhenUserValid: Tests the update method of UserApiService to verify the successful update of a user.
 * - shouldReadAllUserWhenUserValid: Tests the readAll method of UserApiService to verify the retrieval of all users with
 * pagination.
 *
 * <p>InvalidData Inner Class:
 * - shouldReadUserWhenReadUserIsInvalid: Tests the read method of UserApiService to verify that it throws an
 * IllegalArgumentException
 *                                        when attempting to read a non-existent user.
 */

@DisplayName("User Service Test")
public class UserServiceTest {

    /**
     * Inner class ValidData contains test scenarios with valid input data.
     * It uses @Mock and @InjectMocks annotations to create mock instances of UserRepository and UserApiService.
     */

    @Nested
    @ExtendWith({MockitoExtension.class, ValidParameterResolverUser.class})
    public class ValidData {

        @InjectMocks
        private UserApiService userApiService;

        @Mock
        private UserRepository userRepository;

        /**
         * Test the read method of UserApiService to verify the successful retrieval of a user by ID.
         * It builds a User entity and sets up the UserRepository to return the user when queried by ID.
         * The method then compares the retrieved user with the expected user, and the repository's findById method is verified.
         *
         * @param userDto The UserDto object containing user information for the test.
         */
        @Test
        void shouldReadUserWhenUserValid(UserDto userDto) {
            User buildUser = buildUser(userDto);
            when(userRepository.findById(RequestId.VALUE_1.getValue())).thenReturn(Optional.ofNullable(buildUser));
            assertEquals(buildUser, userApiService.read(RequestId.VALUE_1.getValue()));
            verify(userRepository, times(1)).findById(RequestId.VALUE_1.getValue());
        }

        /**
         * Test method to verify the create operation when the input user data is valid.
         * It builds a User entity based on the provided UserDto.
         * The UserRepository is set up to return the buildUser when the save method is called.
         * The method then asserts that the returned ID from the create operation matches RequestId.VALUE_1.getValue().
         * Additionally, it verifies that the UserRepository's save method is called exactly once with any User object.
         *
         * @param userDto The UserDto object containing user information for the test.
         */
        @Test
        void shouldCreateUserWhenUserValid(UserDto userDto) {
            User buildUser = buildUser(userDto);
            when(userRepository.save(any(User.class))).thenReturn(buildUser);
            assertEquals(RequestId.VALUE_1.getValue(), userApiService.create(userDto));
            verify(userRepository, times(1)).save(any(User.class));
        }

        /**
         * Test method to verify the delete operation when the user ID is valid.
         * It builds a User entity based on the provided UserDto and sets up the UserRepository
         * to return the buildUser when queried by the user ID (RequestId.VALUE_1.getValue()).
         * The method then asserts that the delete operation returns true when executed with the valid user ID.
         * Additionally, it verifies that the UserRepository's findById method is called exactly once with RequestId.VALUE_1.getValue().
         *
         * @param userDto The UserDto object containing user information for the test.
         */
        @Test
        void shouldDeleteUserWhenUserValid(UserDto userDto) {
            User buildUser = buildUser(userDto);
            when(userRepository.findById(RequestId.VALUE_1.getValue())).thenReturn(Optional.ofNullable(buildUser));
                    assertTrue(userApiService.delete(RequestId.VALUE_1.getValue()));
            verify(userRepository, times(1)).findById(RequestId.VALUE_1.getValue());
        }

        /**
         * Test method to verify the update operation when the user data is valid.
         * It builds a User entity based on the provided UserDto and sets up the UserRepository
         * to return the buildUser when queried by the user ID (RequestId.VALUE_1.getValue()).
         * The method then asserts that the update operation returns true when executed with the valid user ID and userDto.
         * Additionally, it verifies that the UserRepository's save method is called exactly once with any User object.
         *
         * @param userDto The UserDto object containing user information for the test.
         */
        @Test
        void shouldUpdateUserWhenUserValid(UserDto userDto) {
            User buildUser = buildUser(userDto);
            when(userRepository.findById(RequestId.VALUE_1.getValue())).thenReturn(Optional.ofNullable(buildUser));
            assertTrue( userApiService.update(userDto, RequestId.VALUE_1.getValue()));
            verify(userRepository, times(1)).save(any(User.class));
        }

        /**
         * Test method to verify the readAll operation when the user data is valid.
         * It builds a User entity based on the provided UserDto and creates a list of users (userList) containing the buildUser.
         * The Pageable object (pageable) is set up to represent the first page with 10 items and no sorting.
         * The UserRepository is set up to return a PageImpl containing the userList when queried with the pageable object.
         * The method then asserts that the readAll operation returns the expected userList when executed with the provided pageable.
         * Additionally, it verifies that the UserRepository's findAll method is called exactly once with the pageable object.
         *
         * @param userDto The UserDto object containing user information for the test.
         */
        @Test
        void shouldReadAllUserWhenUserValid(UserDto userDto) {
            User buildUser = buildUser(userDto);
            List<User> userList = new ArrayList<>();
            userList.add(buildUser);
            Pageable pageable = PageRequest.of(0, 10, Sort.unsorted());
            when(userRepository.findAll(pageable))
                    .thenReturn(new PageImpl<>(userList));
            assertEquals(userList, userApiService.readAll(pageable));
            verify(userRepository, times(1)).findAll(pageable);
        }
    }

    /**
     * Inner class InvalidData contains test scenarios with invalid input data.
     * It uses @Mock and @InjectMocks annotations to create mock instances of UserRepository and UserApiService.
     */
    @Nested
    @ExtendWith({MockitoExtension.class, ValidParameterResolverUser.class})
    public class InvalidData {

        @InjectMocks
        private UserApiService userApiService;

        @Mock
        private UserRepository userRepository;

        /**
         * Test the read method of UserApiService to verify that it throws an IllegalArgumentException
         * when attempting to read a non-existent user.
         * The UserRepository is set up to return null when queried by ID, simulating a non-existent user.
         * The test asserts that calling the read method with the non-existent user's ID throws an IllegalArgumentException.
         */
        @Test
        void shouldReadUserWhenReadUserIsInvalid() {
            when(userRepository.findById(1L)) .thenReturn(Optional.ofNullable(null));
            assertThrows(IllegalArgumentException.class, () -> userApiService.read(1L));
        }
    }
}
