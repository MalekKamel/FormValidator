package com.sha.formvalidator.core.validator.pattern

import androidx.core.util.PatternsCompat
import com.sha.formvalidator.core.DefaultErrors

class DomainValidator() : PatternValidator(PatternsCompat.DOMAIN_NAME) {
    override var errorMessage: String = DefaultErrors.domainError
}
