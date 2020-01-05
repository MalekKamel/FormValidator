package com.sha.formvalidator.core.validator

/**
 * A validator that returns true only if the input field contains only numbers.
 *
 */
class PrefixValidator(private val prefix: String, errorMessage: String) : TextValidator(errorMessage) {

    override fun isValid(text: String): Boolean = text.startsWith(prefix)
}
