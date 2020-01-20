package com.sha.formvalidator.core.validator.pattern

import com.sha.formvalidator.core.DefaultErrors
import com.sha.formvalidator.core.validator.ErrorGenerator
import com.sha.formvalidator.core.validator.ErrorGeneratorInterface
import com.sha.formvalidator.core.validator.TextValidator

import java.util.regex.Pattern

/**
 * Base class for regexp based validators.
 *
 * @see DomainValidator
 *
 * @see EmailValidator
 *
 * @see IpAddressValidator
 *
 * @see PhoneValidator
 *
 * @see WebUrlValidator
 */
open class PatternValidator(private val pattern: Pattern) : TextValidator() {

    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.patternError)
    constructor(regex: String) : this(Pattern.compile(regex))

    override fun validate(): Boolean {
        if (value == null) return false
        return pattern.matcher(value!!).matches()
    }

}
