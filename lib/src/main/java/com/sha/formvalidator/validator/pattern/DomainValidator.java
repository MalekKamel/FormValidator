package com.sha.formvalidator.validator.pattern;

import android.util.Patterns;

public class DomainValidator extends PatternValidator {
    public DomainValidator(String errorMessage) {
        super(errorMessage, Patterns.DOMAIN_NAME);
    }
}
