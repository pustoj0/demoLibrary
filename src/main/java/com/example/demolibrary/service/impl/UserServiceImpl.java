package com.example.demolibrary.service.impl;

import com.example.demolibrary.model.Catalog;
import com.example.demolibrary.model.User;
import com.example.demolibrary.repository.UserRepository;
import com.example.demolibrary.service.CatalogService;
import com.example.demolibrary.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private CatalogService catalogService;

    @Override
    public User add(User user) {
        Catalog catalog = new Catalog();
        catalogService.add(catalog);
        user.setCatalog(catalog);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByRecipientId(String recipientId) {
        return userRepository.getUserByRecipientId(recipientId);
    }
}
