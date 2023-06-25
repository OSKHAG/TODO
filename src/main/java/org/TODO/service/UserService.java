package org.TODO.service;

import org.TODO.domain.User;
import org.TODO.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.create(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user, String queryID) {
        return userRepository.update(user, queryID);
    }

    public void deleteUserById(String userId) {
        userRepository.delete(userId);
    }
}
