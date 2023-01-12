package ua.denis.jdbcproject.loginapp.registration.service;

import ua.denis.jdbcproject.db.repository.impl.UserRepository;
import ua.denis.jdbcproject.loginapp.exception.OccupiedNameException;
import ua.denis.jdbcproject.loginapp.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;

@Singleton
public class RegistrationService {
    @Inject
    private UserRepository userRepository;

    public void perform(String username, String password) throws OccupiedNameException {
        if (userRepository.getUserByUsername(username) != null) throw new OccupiedNameException("The name of user is exist.");
        userRepository.saveEntity(User.builder().username(username).password(password).build());
    }
}
