package by.it.academy.UserManagementSystem.service;

import by.it.academy.UserManagementSystem.dto.UserDto;
import by.it.academy.UserManagementSystem.entity.Role;
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
    public long create(UserDto user) {
        User buildUser = buildUser(user);
        return  userRepository.save(buildUser).getId();
    }

    @Override
    public User read(long id){
        return userRepository.findById(id).orElseThrow(()->
        new IllegalArgumentException("Invalid User id:" + id));
    }

    @Override
    public boolean update(UserDto user, Long id) {
        User read = read(id);
        User buildUser = buildUser(user);
        buildUser.setId(read.getId());
        userRepository.save(buildUser);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        read(id);
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public List<User> readAll(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    private User buildUser(UserDto user){
        return User.builder()
                .username(user.getUsername())
                .surname(user.getSurname())
                .role(Role.valueOf(user.getRole()))
                .email(user.getEmail())
                .build();
    }
}
