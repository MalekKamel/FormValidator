package com.sha.formvalidator.core.validator

import com.sha.formvalidator.core.DefaultErrors

/**
 * A simple validator that validates the field only if the field is not empty.
 *
 */
class RequiredValidator : TextValidator() {
    override var errorMessage: String = DefaultErrors.mandatoryError
    override fun validate() = value.trim().isNotEmpty()
}
