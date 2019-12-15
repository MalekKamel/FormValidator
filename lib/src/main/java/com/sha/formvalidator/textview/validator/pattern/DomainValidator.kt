package com.sha.formvalidator.textview.validator.pattern

import androidx.core.util.PatternsCompat

class DomainValidator(errorMessage: String) : PatternValidator(errorMessage, PatternsCompat.DOMAIN_NAME)
