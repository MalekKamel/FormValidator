package com.sha.formvalidator.validator.pattern;

import android.util.Patterns;

/**
 * This validates an email using regexps.
 * Note that if an email passes the validation with this validator it doesn't mean it's a valid email - it means it's a valid email <storng>format</strong>
 *
 */
public class EmailValidator extends PatternValidator {

    public EmailValidator() {
        super(null, Patterns.EMAIL_ADDRESS);
    }

    public EmailValidator(String errorMessage) {
        super(errorMessage, Patterns.EMAIL_ADDRESS);
    }
}
