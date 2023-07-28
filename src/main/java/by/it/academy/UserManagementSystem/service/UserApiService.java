package by.it.academy.UserManagementSystem.service;

import by.it.academy.UserManagementSystem.entity.User;
import by.it.academy.UserManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserApiService implements UserService{

    private final UserRepository userRepository;

    @Override
    public long create(User user) {
        return  userRepository.save(user).getId();
    }

    @Override
    public User read(long id) throws Exception {
        return null;
    }

    @Override
    public boolean update(User user, Long id) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<User> readAll(Pageable pageable) {
        return null;
    }
}
