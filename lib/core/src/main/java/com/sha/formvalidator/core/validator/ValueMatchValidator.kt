package com.sha.formvalidator.core.validator

import com.sha.formvalidator.core.DefaultErrors

/**
 * A simple validator that validates the field only if the value is the same as another one.
 *
 */
class ValueMatchValidator<V>(private val values: () -> List<V?>) : Validator<V> {
    override var value: V? = null
    override var errorMessage: String = DefaultErrors.valueMatchError
    var onError: (() -> Unit)? = null

    override fun validate(): Boolean {
        val values = values()
        if (values.isEmpty()) return false
        val firstValue = values.first()
        val valid =  values.all { it == firstValue }
        if (!valid) onError?.invoke()
        return valid
    }
}
