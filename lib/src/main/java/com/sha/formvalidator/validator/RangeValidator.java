package com.sha.formvalidator.validator;

import android.widget.EditText;

/**
 * A validator that returns true only if the input field contains only numbers
 * and the number is within the given range.
 *
 */
public class RangeValidator extends Validator {

    private int min, max;

    public RangeValidator(String errorMessage, int min, int max) {
        super(errorMessage);
        this.min = min;
        this.max = max;
    }

    public boolean isValid(EditText et) {
        int length = et.getText().toString().length();
        return length >= min && length <= max;
    }
}
