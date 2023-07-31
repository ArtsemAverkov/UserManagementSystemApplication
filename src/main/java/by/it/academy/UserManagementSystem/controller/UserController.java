package by.it.academy.UserManagementSystem.controller;

import by.it.academy.UserManagementSystem.dto.UserDto;
import by.it.academy.UserManagementSystem.entity.User;
import by.it.academy.UserManagementSystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController is a REST controller that handles user-related operations in the User Management System.
 * It provides endpoints to create, read, update, and delete users, as well as retrieve a list of users.
 *
 * <p>This controller class is responsible for handling HTTP requests related to user management.
 * The class is annotated with @RestController, indicating that it is a REST controller that handles HTTP requests
 * and returns JSON responses. All endpoints in this controller are relative to the base path "/users", as specified
 * in the @RequestMapping annotation at the class level.
 *
 */

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Handles user creation.
     * Accepts a JSON representation of UserDto and returns the ID of the created user.
     *
     * @param user The UserDto containing user information to be created.
     * @return The ID of the created user.
     */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Long create(@RequestBody @Valid UserDto user){
     return userService.create(user);
    }

    /**
     * Handles user retrieval by ID.
     * Accepts a path variable 'id' representing the user's ID and returns the corresponding User entity.
     *
     * @param id The ID of the user to retrieve.
     * @return The User entity representing the user with the specified ID.
     */

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private User read (@PathVariable @Valid Long id){
        return userService.read(id);
    }

    /**
     * Update the user details based on the provided UserDto and ID.
     *
     * @param id       The ID of the user to update.
     * @param userDto  The UserDto containing the updated user information.
     * @return True if the update was successful, false otherwise.
     */

    @PatchMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    private boolean update(@PathVariable @Valid Long id, @RequestBody UserDto userDto){
        return userService.update(userDto, id);
    }

    /**
     * Delete the user with the given ID.
     *
     * @param id The ID of the user to delete.
     * @return True if the deletion was successful, false otherwise.
     */


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private boolean delete (@PathVariable @Valid Long id){
        return userService.delete(id);
    }

    /**
     * Retrieve a list of users with pagination support.
     *
     * @param pageable The Pageable object to specify pagination parameters.
     * @return A list of User entities representing the users in the requested page.
     */

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<User> readAll(@PageableDefault Pageable pageable){
        return  userService.readAll(pageable);
    }
}
