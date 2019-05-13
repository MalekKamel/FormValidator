package com.sha.formvalidator.validator.pattern;


public class AlphaNumericValidator extends PatternValidator {

    public AlphaNumericValidator(String errorMessage) {
        super(errorMessage, "[a-zA-Z0-9\u00C0-\u00FF \\./-\\?]*");
    }

}
