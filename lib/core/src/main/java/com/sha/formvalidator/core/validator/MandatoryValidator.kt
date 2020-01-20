package com.sha.formvalidator.core.validator

import com.sha.formvalidator.core.DefaultErrors

/**
 * A simple validator that validates the field only if the field is not empty.
 *
 */
class MandatoryValidator<V> : AbsValidator<V>() {
    override var value: V? = null
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.mandatoryError)
    override fun validate(): Boolean {
        if (value is String) return (value as String).trim().isNotEmpty()
        return value != null
    }
}
