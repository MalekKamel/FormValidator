package com.sha.formvalidator.validator;

import android.widget.EditText;

/**
 * This is a dummy validator. It just returns true on each input.
 *
 */
public class DummyValidator extends Validator {

    public DummyValidator() {
        super(null);
    }

    public boolean isValid(EditText et) {
        return true;
    }
}
