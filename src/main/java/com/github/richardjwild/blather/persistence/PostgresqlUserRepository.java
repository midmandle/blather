package com.github.richardjwild.blather.persistence;

import com.github.richardjwild.blather.user.User;
import com.github.richardjwild.blather.user.UserRepository;

import java.sql.SQLException;
import java.util.Optional;

public class PostgresqlUserRepository implements UserRepository {
    private PostgresConnector postgresDbConnector;

    public PostgresqlUserRepository(PostgresConnector postgresConnector) {
        this.postgresDbConnector = postgresConnector;
    }

    @Override
    public Optional<User> find(String name) {
        User results = null;
        try {
            results = postgresDbConnector.find_user(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(results != null)
            return Optional.of(results);
        return Optional.empty();
    }

    @Override
    public void save(User user){
        try {
            postgresDbConnector.add_user(user.name());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
