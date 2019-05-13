package com.sha.formvalidator.validator.pattern;

import android.util.Patterns;

/**
 * It validates phone numbers.
 * Regexp was taken from the android source code.
 *
 */
public class PhoneValidator extends PatternValidator {

    public PhoneValidator(String errorMessage) {

        // sdd = space, dot, or dash
        // +<digits><sdd>*
        // (<digits>)<sdd>*
        super(errorMessage, Patterns.PHONE);
    }
}
