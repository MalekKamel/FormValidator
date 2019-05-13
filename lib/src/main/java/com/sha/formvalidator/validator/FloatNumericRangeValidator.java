package com.sha.formvalidator.validator;

import android.widget.EditText;

/**
 * A validator that returns true only if the input field contains only numbers
 * and the number is within the given range.
 *
 * @author Said Tahsin Dane <tasomaniac@gmail.com>
 */
public class FloatNumericRangeValidator extends Validator {

    private double floatMin, floatMax;

    public FloatNumericRangeValidator(String errorMessage, double floatMin, double floatMax) {
        super(errorMessage);
        this.floatMin = floatMin;
        this.floatMax = floatMax;
    }

    public boolean isValid(EditText et) {
        try {
            double value = Double.parseDouble(et.getText().toString());
            return value >= floatMin && value <= floatMax;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
