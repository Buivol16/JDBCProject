package ua.denis.jdbcproject.client.service.impl;

import ua.denis.jdbcproject.server.db.repository.impl.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LoginService {
  @Inject
  private UserRepository userRepository;
  public boolean login(String username, String password) {
    var result = false;
    var user = userRepository.findByUsername(username);
    if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) result = true;
    return result;
  }
}
