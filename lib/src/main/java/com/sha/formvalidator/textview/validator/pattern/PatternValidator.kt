package com.sha.formvalidator.textview.validator.pattern

import android.widget.TextView
import com.sha.formvalidator.textview.validator.TextViewValidator

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
open class PatternValidator(_customErrorMessage: String, private val pattern: Pattern) : TextViewValidator(_customErrorMessage) {

    constructor(errorMessage: String, regex: String) : this(errorMessage, Pattern.compile(regex))

    override fun isValid(tv: TextView): Boolean {
        return pattern.matcher(tv.text).matches()
    }

}
