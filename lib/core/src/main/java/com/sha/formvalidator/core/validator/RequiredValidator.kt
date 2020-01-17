package com.sha.formvalidator.core.validator

/**
 * A simple validator that validates the field only if the field is not empty.
 *
 */
class RequiredValidator(errorMessage: String = "") : TextValidator(errorMessage) {

    override fun validate() = value.trim().isNotEmpty()
}
