package com.sha.formvalidator.core.validator

import com.sha.formvalidator.core.DefaultErrors

/**
 * A validator that returns true only if the input field contains only numbers.
 *
 */
class SuffixValidator(private val suffix: String) : TextValidator() {
    override var errorMessage: String = DefaultErrors.suffixError
    override fun validate(): Boolean = value?.endsWith(suffix) == true
}
