package com.sha.formvalidator.validator.pattern;

public class PersonNameValidator extends PatternValidator {

    public PersonNameValidator(String errorMessage) {
        // will allow people with hyphens in his name or surname. Supports also unicode
        super(errorMessage, "[\\p{L}-]+");
    }

}
