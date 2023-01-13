package ua.denis.jdbcproject.client.loginapp.login.gui;

import ua.denis.jdbcproject.client.basicapp.gui.BasicApp;
import ua.denis.jdbcproject.client.exception.BlankFieldException;
import ua.denis.jdbcproject.client.loginapp.common.LabelHandler;
import ua.denis.jdbcproject.client.loginapp.utils.ConstUtils;
import ua.denis.jdbcproject.client.loginapp.registration.gui.RegisterDialog;
import ua.denis.jdbcproject.client.service.impl.FieldValidatorService;
import ua.denis.jdbcproject.client.service.impl.LoginService;
import ua.denis.jdbcproject.server.service.SessionService;

import javax.inject.Inject;
import javax.swing.*;
import java.io.File;

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

  @Inject
  private SessionService sessionService;
  @Inject
  private LoginService loginService;
  @Inject
  private FieldValidatorService fieldValidatorService;
  public LogInWindow() {
    setContentPane(mainPanel);
    if (!new File(ConstUtils.PATH_TO_KEY).exists()) {
      LabelHandler.resetError(errorLabel);
    }else if (sessionService.isExpired()) {
      resetSession();
      LabelHandler.setError(errorLabel, "Your session is already expired.");
    }else {
      changeWindow();
      return;
    }
    setTitle("Please, login into your account.");
    setDefaultCloseOperation(3);
    setVisible(true);
    setSize(400, 600);
    loginButton.addActionListener(e -> login());
    registerButton.addActionListener(e -> new RegisterDialog().showDialog());
  }

  private void login() {
    try{
      fieldValidatorService.checkField(usernameField);
      LabelHandler.resetError(usernameLabel);
    }catch (BlankFieldException e) {
      LabelHandler.setError(usernameLabel, "Field is empty.");
    }
    try {
      fieldValidatorService.checkField(passwordField);
      LabelHandler.resetError(passwordLabel);
    } catch (BlankFieldException e) {
      LabelHandler.setError(passwordLabel, "Field is empty.");
    }
    if (loginService.login(usernameField.getText(), passwordField.getText())) {
      if (rememberMeCheckBox.isSelected()) {
        sessionService.create(usernameField.getText());
        System.out.println("Session successfully created.");
      }
      changeWindow();

    }else {
      LabelHandler.setError(errorLabel, "User is not found. Try to register.");
    }
  }

  private void resetSession() {
    sessionService.delete();
  }
  private void changeWindow(){
    BasicApp.getInstance();
    dispose();
  }
}
