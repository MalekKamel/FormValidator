package com.sha.formvalidator.validator;

import android.widget.EditText;

import com.sha.formvalidator.validator.Validator;

/**
 * It's a validator that applies the "NOT" logical operator to the validator it wraps.
 *
 */
public class InverseValidator extends Validator {
    private Validator v;

    public InverseValidator(Validator validator) {
        super(null);
        this.v = validator;
    }

    public InverseValidator(String errorMessage, Validator validator) {
        super(errorMessage);
        this.v = validator;
    }

    public boolean isValid(EditText et) {
        return !v.isValid(et);
    }

}
