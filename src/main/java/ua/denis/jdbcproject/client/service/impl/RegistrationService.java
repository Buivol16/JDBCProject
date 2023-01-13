package ua.denis.jdbcproject.client.service.impl;

import lombok.NoArgsConstructor;
import ua.denis.jdbcproject.server.db.repository.impl.UserRepository;
import ua.denis.jdbcproject.client.loginapp.exception.OccupiedNameException;
import ua.denis.jdbcproject.server.db.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@NoArgsConstructor
public class RegistrationService {
    @Inject
    private UserRepository userRepository;

    public void perform(String username, String password) throws OccupiedNameException {
        if (userRepository.findByUsername(username) != null) throw new OccupiedNameException("The name of user is exist.");
        userRepository.saveEntity(User.builder().username(username).password(password).build());
    }
}
