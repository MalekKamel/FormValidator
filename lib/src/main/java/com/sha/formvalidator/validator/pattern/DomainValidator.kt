package com.sha.formvalidator.validator.pattern

import android.util.Patterns

class DomainValidator(errorMessage: String) : PatternValidator(errorMessage, Patterns.DOMAIN_NAME)
