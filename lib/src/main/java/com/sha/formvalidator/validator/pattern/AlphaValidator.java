package com.sha.formvalidator.validator.pattern;

public class AlphaValidator extends PatternValidator {
    public AlphaValidator(String errorMessage) {
        super(errorMessage, "[A-z\u00C0-\u00ff \\./-\\?]*");
    }
}
