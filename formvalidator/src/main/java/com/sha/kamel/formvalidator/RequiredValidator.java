package com.sha.kamel.formvalidator;

import android.widget.EditText;

/**
 * Created by Sha on 11/8/17.
 */

public class RequiredValidator extends Validator{

    public RequiredValidator(EditText et) {
        super(et);
    }

    @Override
    protected boolean validate(String text) {
        // Return true ? yes, we handle 'required' in base Validator.
        return true;
    }
}
