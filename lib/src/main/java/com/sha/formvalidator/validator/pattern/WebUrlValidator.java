package com.sha.formvalidator.validator.pattern;

import android.util.Patterns;

/**
 * Validates a web url in the format:
 * scheme + authority + path
 *
 */
public class WebUrlValidator extends PatternValidator {

    public WebUrlValidator(String errorMessage) {
        super(errorMessage, Patterns.WEB_URL);
    }

}
