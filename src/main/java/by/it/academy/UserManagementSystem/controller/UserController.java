package by.it.academy.UserManagementSystem.controller;

import by.it.academy.UserManagementSystem.dto.UserDto;
import by.it.academy.UserManagementSystem.entity.User;
import by.it.academy.UserManagementSystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Long create(@RequestBody @Valid UserDto user){
     return userService.create(user);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private User read (@PathVariable @Valid Long id){
        return userService.read(id);
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    private boolean update(@PathVariable @Valid Long id, @RequestBody UserDto userDto){
        return userService.update(userDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private boolean delete (@PathVariable @Valid Long id){
        return userService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<User> readAll(@PageableDefault Pageable pageable){
        return  userService.readAll(pageable);
    }
}
