package com.sha.formvalidator.textview.validator.pattern

import com.sha.formvalidator.textview.validator.TextValidator

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
open class PatternValidator(_customErrorMessage: String, private val pattern: Pattern) : TextValidator(_customErrorMessage) {

    constructor(errorMessage: String, regex: String) : this(errorMessage, Pattern.compile(regex))

    override fun isValid(text: String): Boolean {
        return pattern.matcher(text).matches()
    }

}
