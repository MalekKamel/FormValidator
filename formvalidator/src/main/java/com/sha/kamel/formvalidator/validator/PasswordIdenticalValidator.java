package com.sha.kamel.formvalidator.validator;

import android.widget.EditText;

import com.sha.kamel.formvalidator.Validator;

/**
 * Created by Sha on 11/8/17.
 */

public class PasswordIdenticalValidator extends Validator implements PasswordIdentical {

    public PasswordIdenticalValidator(EditText et) {
        super(et);
    }

    @Override
    protected boolean validate(String text) {
        return true;
    }
}
