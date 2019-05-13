package com.sha.formvalidator.validator;

import android.widget.EditText;

/**
 * A validator that returns true only if the input field contains only numbers
 * and the number is within the given range.
 *
 */
public class NumericRangeValidator extends Validator {

    private long min, max;

    public NumericRangeValidator(String errorMessage, long min, long max) {
        super(errorMessage);
        this.min = min;
        this.max = max;
    }

    public boolean isValid(EditText et) {
        try {
            long value = Long.parseLong(et.getText().toString());
            return value >= min && value <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
