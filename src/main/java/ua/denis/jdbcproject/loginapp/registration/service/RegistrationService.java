package ua.denis.jdbcproject.loginapp.registration.service;

import ua.denis.jdbcproject.db.DBHandler;
import ua.denis.jdbcproject.db.repository.CRUDRepository;
import ua.denis.jdbcproject.db.repository.impl.UserRepository;
import ua.denis.jdbcproject.loginapp.exception.OccupiedNameException;
import ua.denis.jdbcproject.loginapp.model.User;

import java.sql.SQLException;

public class RegistrationService {
    private static RegistrationService INSTANCE = null;

    private RegistrationService(){}
    public static RegistrationService getInstance(){
        if (INSTANCE == null) INSTANCE = new RegistrationService();
        return INSTANCE;
    }

    public void perform(String username, String password) throws SQLException, OccupiedNameException {
        if (UserRepository.getUserByUsername(username) != null) throw new OccupiedNameException("The name of user is exist.");
        UserRepository(User.builder().username(username).password(password).build());
    }
}
