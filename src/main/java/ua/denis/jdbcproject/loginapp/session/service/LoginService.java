package ua.denis.jdbcproject.loginapp.session.service;

import ua.denis.jdbcproject.db.repository.UserRepository;

public class LoginService {
  public static boolean login(String username, String password) {
    var result = false;
    var user = UserRepository.getUserByUsername(username);
    if (user.getUsername().equals(username) && user.getPassword().equals(password)) result = true;
    return result;
  }
}
