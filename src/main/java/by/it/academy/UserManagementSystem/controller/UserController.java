package by.it.academy.UserManagementSystem.controller;

import by.it.academy.UserManagementSystem.entity.User;
import by.it.academy.UserManagementSystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Long create(@RequestBody @Valid User user){
     return userService.create(user);
    }
}
