package com.example.demolibrary.repository;

import com.example.demolibrary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM User u WHERE u.recipientId=?1")
    Optional<User> getUserByRecipientId(String recipientId);
}
