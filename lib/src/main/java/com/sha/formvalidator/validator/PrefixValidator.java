package com.sha.formvalidator.validator;

import android.widget.EditText;

/**
 * A validator that returns true only if the input field contains only numbers.
 *
 */
public class PrefixValidator extends Validator {

    private String prefix;

    public PrefixValidator(String prefix, String errorMessage) {
        super(errorMessage);
        this.prefix = prefix;
    }

    public boolean isValid(EditText et) {
        return et.getText().toString().startsWith(prefix);
    }
}
