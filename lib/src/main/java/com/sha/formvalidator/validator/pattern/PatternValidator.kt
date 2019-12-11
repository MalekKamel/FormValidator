package com.sha.formvalidator.validator.pattern

import android.widget.EditText

import com.sha.formvalidator.validator.Validator

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
open class PatternValidator(_customErrorMessage: String, private val pattern: Pattern) : Validator(_customErrorMessage) {

    constructor(errorMessage: String, regex: String) : this(errorMessage, Pattern.compile(regex)) {}

    override fun isValid(et: EditText): Boolean {
        return pattern.matcher(et.text).matches()
    }

}
