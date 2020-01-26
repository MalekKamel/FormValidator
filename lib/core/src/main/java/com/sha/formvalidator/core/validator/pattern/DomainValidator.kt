package com.sha.formvalidator.core.validator.pattern

import androidx.core.util.PatternsCompat
import com.sha.formvalidator.core.DefaultErrors
import com.sha.formvalidator.core.validator.ErrorGenerator
import com.sha.formvalidator.core.validator.ErrorGeneratorInterface

class DomainValidator() : PatternValidator(PatternsCompat.DOMAIN_NAME) {
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.domain)
}
