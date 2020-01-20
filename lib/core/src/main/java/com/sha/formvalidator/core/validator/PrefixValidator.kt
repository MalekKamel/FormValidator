package com.sha.formvalidator.core.validator

import com.sha.formvalidator.core.DefaultErrors

/**
 * A validator that returns true only if the input field contains only numbers.
 *
 */
class PrefixValidator(private val prefix: String) : TextValidator() {
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.prefixError)
    override fun validate(): Boolean = value?.startsWith(prefix) == true
}
