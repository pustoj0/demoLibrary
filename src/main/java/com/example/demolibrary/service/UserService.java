package com.example.demolibrary.service;

import com.example.demolibrary.model.User;

import java.util.Optional;

public interface UserService {
    User add(User user);

    Optional<User> getUserByRecipientId(String recipientId);
}
