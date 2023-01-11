package ua.denis.jdbcproject;

import ua.denis.jdbcproject.db.DBHandler;
import ua.denis.jdbcproject.loginapp.login.gui.LogInWindow;


public class Main {
  public static void main(String[] args) {
    DBHandler.getInstance();
    new LogInWindow();
  }
}
