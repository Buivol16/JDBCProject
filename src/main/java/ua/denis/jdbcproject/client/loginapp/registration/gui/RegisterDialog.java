package ua.denis.jdbcproject.client.loginapp.registration.gui;

import ua.denis.jdbcproject.client.exception.BlankFieldException;
import ua.denis.jdbcproject.client.loginapp.common.LabelHandler;
import ua.denis.jdbcproject.client.loginapp.exception.OccupiedNameException;
import ua.denis.jdbcproject.client.service.impl.FieldValidatorService;
import ua.denis.jdbcproject.client.service.impl.RegistrationService;

import javax.swing.*;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class RegisterDialog extends JDialog {
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerMeButton;
    private JButton cancelButton;
    private JLabel usernameErrorLabel;
    private JLabel passwordErrorLabel;
    private JLabel errorLabel;

    private RegistrationService registrationService = RegistrationService.getInstance();
    private FieldValidatorService fieldValidatorService = FieldValidatorService.getInstance();

    public RegisterDialog() {
        setModal(true);
        setContentPane(contentPane);
        registerMeButton.addActionListener(
                e -> {
                    var result = false;
                    try {
                        fieldValidatorService.checkField(usernameField);
                        LabelHandler.resetError(usernameErrorLabel);
                    } catch (BlankFieldException ex) {
                        LabelHandler.setError(usernameErrorLabel, "Username field is empty.");
                    }
                    try {
                        fieldValidatorService.checkField(passwordField);
                        LabelHandler.resetError(passwordErrorLabel);
                        result = true;
                    } catch (BlankFieldException ex) {
                        LabelHandler.setError(passwordErrorLabel, "Password field is empty.");
                    }
                    if (result) {
                        register();
                    }
                });
        cancelButton.addActionListener(e -> dispose());
    }

    public void showDialog() {
        RegisterDialog dialog = new RegisterDialog();
        dialog.setSize(400, 400);
        dialog.setVisible(true);
    }

    private void register() {
        try {
            registrationService.perform(usernameField.getText(), passwordField.getText());
        } catch (OccupiedNameException exception) {
            LabelHandler.setError(errorLabel, exception.getMessage());
            return;
        }
        showMessageDialog(null, "You are successfully registered!", "Success", INFORMATION_MESSAGE);
        dispose();
    }
}
