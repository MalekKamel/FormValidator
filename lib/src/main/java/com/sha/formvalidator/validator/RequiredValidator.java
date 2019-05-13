package com.sha.formvalidator.validator;

import android.widget.EditText;

/**
 * A simple validator that validates the field only if the field is not empty.
 *
 */
public class RequiredValidator extends Validator {

    public RequiredValidator() {
        super(null);
    }

      public RequiredValidator(String errorMessage) {
        super(errorMessage);
    }

    public boolean isValid(EditText et) {
        return !et.getText().toString().trim().isEmpty();
    }
}
