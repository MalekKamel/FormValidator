package com.sha.formvalidator.core.validator

import com.sha.formvalidator.core.DefaultErrors
import java.util.*

/**
 * A validator that returns true only if the input field contains only numbers.
 *
 */
class PrefixValidator(private var prefix: String, private val ignoreCase: Boolean = false) : TextValidator() {
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.prefixError)
    override fun validate(): Boolean {
        if (ignoreCase) {
            value = value?.toLowerCase(Locale.ROOT)
            prefix = prefix.toLowerCase(Locale.ROOT)
        }
        return value?.startsWith(prefix) == true
    }
}
