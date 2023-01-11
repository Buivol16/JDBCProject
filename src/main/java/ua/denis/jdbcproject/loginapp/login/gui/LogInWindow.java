package ua.denis.jdbcproject.loginapp.login.gui;

import javax.swing.*;

import ua.denis.jdbcproject.basicapp.gui.BasicApp;
import ua.denis.jdbcproject.db.DBHandler;
import ua.denis.jdbcproject.loginapp.common.LabelHandler;
import ua.denis.jdbcproject.db.DbHelper;
import ua.denis.jdbcproject.loginapp.registration.gui.RegisterDialog;
import ua.denis.jdbcproject.loginapp.session.service.LoginService;
import ua.denis.jdbcproject.loginapp.session.service.SessionService;
import ua.denis.jdbcproject.loginapp.utils.ConstUtils;
import ua.denis.jdbcproject.loginapp.utils.FieldValidation;

import java.io.File;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class LogInWindow extends JFrame {
  private JPanel mainPanel;
  private JTextField usernameField;
  private JPasswordField passwordField;
  private JButton loginButton;
  private JCheckBox rememberMeCheckBox;
  private JButton registerButton;
  private JLabel passwordLabel;
  private JLabel usernameLabel;
  private JLabel errorLabel;

  public LogInWindow() {
    if (!new File(ConstUtils.PATH_TO_KEY).exists()) {
      LabelHandler.resetError(errorLabel);
    }else if (SessionService.getInstance().isExpired()) {
      resetSession();
      LabelHandler.setError(errorLabel, "Your session is already expired.");
    }else {
      changeWindow();
      return;
    }
    setTitle("Please, login into your account.");
    setContentPane(mainPanel);
    setDefaultCloseOperation(3);
    setVisible(true);
    setSize(400, 600);
    loginButton.addActionListener(e -> login());
    registerButton.addActionListener(e -> new RegisterDialog().showDialog());
  }

  private void login() {
    if (!FieldValidation.isNotEmpty(usernameField)) {
      LabelHandler.setError(usernameLabel, "Field is empty.");
    } else LabelHandler.resetError(usernameLabel);

    if (!FieldValidation.isNotEmpty(passwordField)) {
      LabelHandler.setError(passwordLabel, "Field is empty.");
    } else LabelHandler.resetError(passwordLabel);
    try {
      if (LoginService.login(usernameField.getText(), passwordField.getText())) {
        if (rememberMeCheckBox.isSelected()) {
          try {
            SessionService.getInstance().create(usernameField.getText());
            System.out.println("Session successfully created.");
          } catch (SQLIntegrityConstraintViolationException e) {
            LabelHandler.setError(errorLabel, "User with that name is already login");
            return;
          }
        }
        changeWindow();
        return;
      }else {
        LabelHandler.setError(errorLabel, "User is not found. Try to register.");
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  private void resetSession() {
    SessionService.getInstance().delete();
  }
  private void changeWindow(){
    BasicApp.getInstance();
    dispose();
  }
}
