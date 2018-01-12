package com.sha.kamel.formvalidator.validator;

import android.widget.EditText;

import com.sha.kamel.formvalidator.Validator;

/**
 * Created by Sha on 11/8/17.
 */

public class FixedLengthValidator extends Validator {

    private int length;

    public FixedLengthValidator(EditText et, int length) {
        super(et);
        this.length = length;
    }

    @Override
    protected boolean validate(String text) {
        if (text.length() != length){
            errorMessage = "Characters Must be " + length;
            return false;
        }
        return true;
    }

}
