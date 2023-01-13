package ua.denis.jdbcproject;

import ua.denis.jdbcproject.server.db.DBHandler;
import ua.denis.jdbcproject.client.loginapp.login.gui.LogInWindow;


public class Main {
  public static void main(String[] args) {
    DBHandler.getInstance();
    new LogInWindow();
  }
}
