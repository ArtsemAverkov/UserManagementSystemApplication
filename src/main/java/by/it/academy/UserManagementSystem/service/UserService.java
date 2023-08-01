package by.it.academy.UserManagementSystem.service;

import by.it.academy.UserManagementSystem.dto.UserDto;
import by.it.academy.UserManagementSystem.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    long create(UserDto user);
    User read (long id);
    boolean update (UserDto user, Long id);
    boolean delete (Long id);
    List<User> readAll (Pageable pageable);
}
