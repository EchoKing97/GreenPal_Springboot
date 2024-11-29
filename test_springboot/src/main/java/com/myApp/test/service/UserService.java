package com.myApp.test.service;

import com.myApp.test.model.User;
import com.myApp.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final UserRepository repository;

    public boolean exists(User user){
        return repository.existsByName(user.getName());
    }

    public User findByNameAndPassword(User user){
        return repository.findByNameAndPassword(user.getName(),user.getPassword());
    }

    public User findByName(String username) {
        return repository.findByName(username);
    }

    public boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    public User getUserById(int userId) {
        Optional<User> userOptional = repository.findById(userId);
        return userOptional.orElse(null);
    }

    public boolean insert(User user){
        repository.save(user);
        return true;
    }

    public boolean update(User user) {
        User existingUser = repository.findById(user.getId()).orElse(null);
        if (existingUser == null) {
            return false;
        }
        existingUser.setName(user.getName());
        existingUser.setPassword(user.getPassword());
        existingUser.setPhone(user.getPhone());
        repository.save(existingUser);
        return true;
    }
}