package com.sha.formvalidator.validator;

import android.text.TextUtils;
import android.widget.EditText;

/**
 * A validator that returns true only if the input field contains only numbers.
 *
 */
public class NumericValidator extends Validator {

    public NumericValidator(String errorMessage) {
        super(errorMessage);
    }

    public boolean isValid(EditText et) {
        return TextUtils.isDigitsOnly(et.getText());
    }
}
