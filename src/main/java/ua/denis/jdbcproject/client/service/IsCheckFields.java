package ua.denis.jdbcproject.client.service;

import ua.denis.jdbcproject.client.exception.BlankFieldException;

import javax.swing.*;

public interface IsCheckFields {
    void checkField(JTextField... textFields) throws BlankFieldException;
}
