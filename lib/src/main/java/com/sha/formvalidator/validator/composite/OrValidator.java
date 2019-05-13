package com.sha.formvalidator.validator.composite;

import android.widget.EditText;

import com.sha.formvalidator.validator.Validator;

/**
 * The or validator checks if one of passed validators is returning true.<br/>
 * Validator's priority is maintained by index, the lower index is the higher priority.
 * Note: the message that will be shown is the one passed to the Constructor
 *
 */
public class OrValidator extends CompositeValidator {

    public OrValidator(String message, Validator... validators) {
        super(message, validators);
    }

    public boolean isValid(EditText et) {
        for (Validator v : validators)
            if (v.isValid(et)) return true; // Remember :) We're acting like an || operator.

        return false;
    }

}

