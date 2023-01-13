package ua.denis.jdbcproject.client.service.impl;

import ua.denis.jdbcproject.client.exception.BlankFieldException;
import ua.denis.jdbcproject.client.service.IsCheckFields;
import ua.denis.jdbcproject.client.service.IsCheckJLists;
import ua.denis.jdbcproject.server.db.repository.impl.UserRepository;

import javax.swing.*;


public class FieldValidatorService implements IsCheckFields, IsCheckJLists {
    private static FieldValidatorService INSTANCE = null;

    private FieldValidatorService(){}

    public static FieldValidatorService getInstance(){
        if (INSTANCE == null) INSTANCE = new FieldValidatorService();
        return INSTANCE;
    }

    @Override
    public void checkField(JTextField... textFields) throws BlankFieldException {
        for(var textField : textFields){
            if(textField.getText().isBlank()) throw new BlankFieldException("One more field is blank.");
        }
    }

    @Override
    public void checkList(JList... lists) throws BlankFieldException {
        for(var ob : lists){
            if(ob.getSelectedValue() == null) throw new BlankFieldException("One more list is blank");
        }
    }
}
