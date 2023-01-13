package ua.denis.jdbcproject.client.service.impl;

import lombok.NoArgsConstructor;
import ua.denis.jdbcproject.client.exception.BlankFieldException;
import ua.denis.jdbcproject.client.service.IsCheckFields;
import ua.denis.jdbcproject.client.service.IsCheckJLists;

import javax.inject.Singleton;
import javax.swing.*;
@Singleton
@NoArgsConstructor
public class FieldValidatorService implements IsCheckFields, IsCheckJLists {


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
