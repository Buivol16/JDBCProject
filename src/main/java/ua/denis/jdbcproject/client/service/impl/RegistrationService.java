package ua.denis.jdbcproject.client.service.impl;

import ua.denis.jdbcproject.client.loginapp.exception.OccupiedNameException;
import ua.denis.jdbcproject.server.db.model.User;
import ua.denis.jdbcproject.server.db.repository.impl.UserRepository;


public class RegistrationService {
    private static RegistrationService INSTANCE = null;

    private RegistrationService(){}

    public static RegistrationService getInstance(){
        if (INSTANCE == null) INSTANCE = new RegistrationService();
        return INSTANCE;
    }
    private UserRepository userRepository = UserRepository.getInstance();

    public void perform(String username, String password) throws OccupiedNameException {
        if (userRepository.findByUsername(username) != null) throw new OccupiedNameException("The name of user is exist.");
        userRepository.saveEntity(User.builder().username(username).password(password).build());
    }
}
