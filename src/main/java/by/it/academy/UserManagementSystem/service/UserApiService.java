package by.it.academy.UserManagementSystem.service;

import by.it.academy.UserManagementSystem.dto.UserDto;
import by.it.academy.UserManagementSystem.entity.Role;
import by.it.academy.UserManagementSystem.entity.User;
import by.it.academy.UserManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserApiService is a service class that provides implementations for user-related operations in the User Management System.
 * It implements the UserService interface to handle CRUD (Create, Read, Update, Delete) operations for users.
 *
 * <p>This service class is responsible for interacting with the UserRepository to perform database operations.
 * It is annotated with @Service, indicating that it is a Spring service component and should be automatically discovered
 * and registered as a bean in the Spring application context.
 *
 * <p>The class uses Lombok's @RequiredArgsConstructor to automatically generate a constructor with the final field 'userRepository',
 * which is required for the class to function properly.
 */

@Service
@RequiredArgsConstructor
public class UserApiService implements UserService{

    private final UserRepository userRepository;

    /**
     * Create a new user based on the provided UserDto and save it to the database.
     *
     * @param user The UserDto containing user information to be created.
     * @return The ID of the created user.
     */

    @Override
    public long create(UserDto user) {
        User buildUser = buildUser(user);
        return  userRepository.save(buildUser).getId();
    }

    /**
     * Read the user details by the given ID from the database.
     *
     * @param id The ID of the user to retrieve.
     * @return The User entity representing the user with the specified ID.
     * @throws IllegalArgumentException if the user with the given ID does not exist in the database.
     */

    @Override
    public User read(long id){
        return userRepository.findById(id).orElseThrow(()->
        new IllegalArgumentException("Invalid User id:" + id));
    }

    /**
     * Update the user details based on the provided UserDto and ID in the database.
     *
     * @param user The UserDto containing the updated user information.
     * @param id   The ID of the user to update.
     * @return True if the update was successful, false otherwise.
     * @throws IllegalArgumentException if the user with the given ID does not exist in the database.
     */

    @Override
    public boolean update(UserDto user, Long id) {
        User read = read(id);
        User buildUser = buildUser(user);
        buildUser.setId(read.getId());
        userRepository.save(buildUser);
        return true;
    }

    /**
     * Delete the user with the given ID from the database.
     *
     * @param id The ID of the user to delete.
     * @return True if the deletion was successful, false otherwise.
     * @throws IllegalArgumentException if the user with the given ID does not exist in the database.
     */

    @Override
    public boolean delete(Long id) {
        read(id);
        userRepository.deleteById(id);
        return true;
    }

    /**
     * Retrieve a list of users with pagination support from the database.
     *
     * @param pageable The Pageable object to specify pagination parameters.
     * @return A list of User entities representing the users in the requested page.
     */

    @Override
    public List<User> readAll(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    /**
     * Builds a User entity from the provided UserDto.
     *
     * @param user The UserDto containing user information.
     * @return The User entity representing the user with the data from the UserDto.
     */

    private User buildUser(UserDto user){
        return User.builder()
                .username(user.getUsername())
                .surname(user.getSurname())
                .role(Role.valueOf(user.getRole()))
                .email(user.getEmail())
                .build();
    }
}
