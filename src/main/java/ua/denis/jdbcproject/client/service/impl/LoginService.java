package ua.denis.jdbcproject.client.service.impl;

import ua.denis.jdbcproject.server.db.repository.impl.UserRepository;

public class LoginService {

  private static LoginService INSTANCE = null;

  private LoginService(){}

  public static LoginService getInstance(){
    if (INSTANCE == null) INSTANCE = new LoginService();
    return INSTANCE;
  }
  private UserRepository userRepository;
  public boolean login(String username, String password) {
    var result = false;
    var user = userRepository.findByUsername(username);
    if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) result = true;
    return result;
  }
}
