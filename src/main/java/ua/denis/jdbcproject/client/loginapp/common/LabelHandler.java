package ua.denis.jdbcproject.client.loginapp.common;

import ua.denis.jdbcproject.client.loginapp.utils.ConstUtils;

import javax.swing.*;

public class LabelHandler {
    public static void setError(JLabel labelOfOutput, String textOfError) {
        labelOfOutput.setForeground(ConstUtils.ERROR_COLOR);
        labelOfOutput.setText(textOfError);
    }

    public static void resetError(JLabel... labelOfOutput) {
        for (var label : labelOfOutput) {
            try {
                label.setText("");
            }catch (NullPointerException e){

            }
        }
    }
    public static void setSuccess(JLabel labelOfOutput, String textOfSuccess){
        labelOfOutput.setForeground(ConstUtils.SUCCESS_COLOR);
        labelOfOutput.setText(textOfSuccess);
    }

}
