package com.sha.formvalidator.core.validator

import com.sha.formvalidator.core.DefaultErrors
import java.util.*

/**
 * A validator that returns true only if the input field contains only numbers.
 *
 */
class SuffixValidator(private var suffix: String, private val ignoreCase: Boolean = false) : AbsValidator<String>() {
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.suffix)
    override fun validate(): Boolean {
        if (ignoreCase) {
            value = value?.toLowerCase(Locale.ROOT)
            suffix = suffix.toLowerCase(Locale.ROOT)
        }
       return value?.endsWith(suffix) == true
    }
}
