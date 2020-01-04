package com.sha.formvalidator.core.text.validator

/**
 * A simple validator that validates the field only if the field is not empty.
 *
 */
class RequiredValidator(errorMessage: String = "") : TextValidator(errorMessage) {

    override fun isValid(text: String): Boolean = text.trim { it <= ' ' }.isNotEmpty()
}
