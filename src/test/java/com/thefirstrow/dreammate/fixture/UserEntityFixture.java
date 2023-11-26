package com.thefirstrow.dreammate.fixture;

import com.thefirstrow.dreammate.model.UserRole;
import com.thefirstrow.dreammate.model.entity.UserEntity;

import java.sql.Timestamp;
import java.time.Instant;

public class UserEntityFixture {

    public static UserEntity get(String email, String password) {
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setEmail(email);
        entity.setPassword(password);
        entity.setRole(UserRole.USER);
        entity.setRegisteredAt(Timestamp.from(Instant.now()));
        return entity;
    }
}
