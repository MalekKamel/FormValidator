package com.sha.formvalidator.validator.pattern;

public class PersonFullNameValidator extends PatternValidator {

    public PersonFullNameValidator(String errorMessage) {
        // will allow people with hyphens in his name or surname. Supports also unicode
        super(errorMessage, "[\\p{L}- ]+");
    }

}
