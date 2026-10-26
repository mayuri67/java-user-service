package com.example.userservice.service;

import com.example.userservice.model.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();

    public UserService() {
        users.add(new User(1L, "Nikhil", "nikhil@example.com"));
        users.add(new User(2L, "Mayuri", "mayuri@example.com"));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User createUser(User user) {
        long nextId = users.stream()
                .map(User::getId)
                .filter(id -> id != null)
                .mapToLong(Long::longValue)
                .max()
                .orElse(0L) + 1;

        user.setId(nextId);
        users.add(user);
        return user;
    }
}
