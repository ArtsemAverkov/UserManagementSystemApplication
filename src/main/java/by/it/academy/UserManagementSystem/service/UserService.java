package by.it.academy.UserManagementSystem.service;

import by.it.academy.UserManagementSystem.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    long create(User user);
    User read (long id) throws Exception;
    boolean update (User user, Long id);
    boolean delete (Long id);
    List<User> readAll (Pageable pageable);
}
