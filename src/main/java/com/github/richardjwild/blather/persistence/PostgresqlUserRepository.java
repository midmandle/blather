package com.github.richardjwild.blather.persistence;

import com.github.richardjwild.blather.user.User;
import com.github.richardjwild.blather.user.UserRepository;

import java.util.Optional;

public class PostgresqlUserRepository implements UserRepository {
    private PostgresConnector postgresDbConnector;

    public PostgresqlUserRepository() {
        this.postgresDbConnector = postgresDbConnector;
    }

    @Override
    public Optional<User> find(String name) {
        return Optional.empty();
    }

    @Override
    public void save(User user) {
       postgresDbConnector.add_user(user.name());
    }
}
