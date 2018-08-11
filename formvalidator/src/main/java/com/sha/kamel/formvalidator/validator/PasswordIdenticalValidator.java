package com.sha.kamel.formvalidator.validator;

import android.widget.TextView;

import com.sha.kamel.formvalidator.Validator;

/**
 * Created by Sha on 11/8/17.
 */

public class PasswordIdenticalValidator extends Validator implements PasswordIdentical {

    public PasswordIdenticalValidator(TextView tv) {
        super(tv);
    }

    @Override
    protected boolean validate(String text) {
        return true;
    }

    @Override
    public String getErrorMessage() {
        return "Passwords aren't identical.";
    }
}
