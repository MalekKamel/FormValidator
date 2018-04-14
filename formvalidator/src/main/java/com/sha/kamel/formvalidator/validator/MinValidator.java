package com.sha.kamel.formvalidator.validator;

import android.widget.EditText;

import com.sha.kamel.formvalidator.Validator;

/**
 * Created by Sha on 11/8/17.
 */

public class MinValidator extends Validator {

    private int min;
    private String minError = "Characters can't be greater than";

    public MinValidator(EditText et, int min) {
        super(et);
        this.min = min;
    }

    @Override
    protected boolean validate(String text) {
        int length = text.length();
        
        if (length < min) {
            errorMessage = minError + " " + min;
            return false;
        }

        return true;
    }

    public MinValidator minErrorMessage(String msg){
        minError = msg;
        return this;
    }

}
