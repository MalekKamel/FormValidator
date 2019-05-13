package com.sha.formvalidator.validator;

import android.widget.EditText;

import java.util.Arrays;
import java.util.List;

/**
 * A simple validator that validates the field only if the value is the same as another one.
 *
 */
public class ValueMatchValidator extends Validator {
    private final List<EditText> ets;

    public ValueMatchValidator(String errorMessage, EditText... ets) {
        super(errorMessage);
        this.ets = Arrays.asList(ets);
    }

    @Override
    public boolean isValid(EditText editText) {
       String value = editText.getText().toString();
        for (EditText et : ets)
            if (!et.getText().toString().equals(value))
                return false;
        return true;
    }
}
