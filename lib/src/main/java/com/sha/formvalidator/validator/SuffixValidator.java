package com.sha.formvalidator.validator;

import android.widget.EditText;

/**
 * A validator that returns true only if the input field contains only numbers.
 *
 */
public class SuffixValidator extends Validator {

    private String suffix;

    public SuffixValidator(String suffix, String errorMessage) {
        super(errorMessage);
        this.suffix = suffix;
    }

    public boolean isValid(EditText et) {
        return et.getText().toString().endsWith(suffix);
    }
}
