package ua.denis.jdbcproject.client.service;

import ua.denis.jdbcproject.client.exception.BlankFieldException;

import javax.swing.*;

public interface IsCheckJLists {
    void checkList(JList... lists) throws BlankFieldException;
}
