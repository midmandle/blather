package com.github.richardjwild.blather.persistence;

import com.github.richardjwild.blather.user.User;
import com.github.richardjwild.blather.user.UserRepository;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class PostgresqlUserRepositoryShould {
    @Test
    public void adds_a_new_user_to_the_persistent_store() {
        PostgresConnector postgresDbConnector = Mockito.mock(PostgresConnector.class);
        UserRepository postgresUserRepository = new PostgresqlUserRepository(postgresDbConnector);
        User user = new User("user_name");

        postgresUserRepository.save(user);

        verify(postgresDbConnector).add_user("user_name");
    }
}
