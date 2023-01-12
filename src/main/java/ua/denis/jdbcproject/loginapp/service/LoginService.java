package ua.denis.jdbcproject.loginapp.service;

import ua.denis.jdbcproject.db.repository.impl.UserRepository;

public class LoginService {
  public static boolean login(String username, String password) {
    var result = false;
    var user = UserRepository.getUserByUsername(username);
    if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) result = true;
    return result;
  }
}
