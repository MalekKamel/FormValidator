package com.sha.formvalidator.core.validator.pattern

import android.util.Patterns

/**
 * This validates an email using regexps.
 * Note that if an email passes the validation with this validator it doesn't mean it's a valid email - it means it's a valid email <storng>format
 *
</storng> */
class EmailValidator(errorMessage: String = "") : PatternValidator(errorMessage, Patterns.EMAIL_ADDRESS) {
}
