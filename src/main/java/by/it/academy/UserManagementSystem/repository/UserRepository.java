package by.it.academy.UserManagementSystem.repository;

import by.it.academy.UserManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
