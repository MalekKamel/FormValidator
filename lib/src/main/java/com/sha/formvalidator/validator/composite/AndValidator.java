package com.sha.formvalidator.validator.composite;

import android.widget.EditText;

import com.sha.formvalidator.validator.Validator;

/**
 * The AND validator checks if all of the passed validators is returning true.<br/>
 * Note: the message that will be shown is the one of the first failing validator
 *
 */
public class AndValidator extends CompositeValidator {

    public AndValidator(Validator... validators) {
        super(null, validators);
    }

    public AndValidator() {
        super(null);
    }

    public boolean isValid(EditText et) {
        for (Validator v : validators) {
            if (!v.isValid(et)) {
                this.errorMessage = v.getErrorMessage();
                return false; // Remember :) We're acting like an || operator.
            }
        }
        return true;
    }
}

