package com.sha.kamel.formvalidator.validator;

import android.widget.EditText;

import com.sha.kamel.formvalidator.Validator;

/**
 * Created by Sha on 11/8/17.
 */

public class RequiredValidator extends Validator {

    public RequiredValidator(EditText et) {
        super(et);
    }

    @Override
    protected boolean validate(String text) {
        // Return true ? yes, we handle 'required' in base Validator.
        return true;
    }
}
