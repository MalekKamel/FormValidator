package com.sha.formvalidator.core.validator

import com.sha.formvalidator.core.DefaultErrors

/**
 * A simple validator that validates the field only if the field is not empty.
 *
 */
class MandatoryValidator<V>(private val invalidValue: V? = null) : AbsValidator<V>() {
    override var value: V? = null
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.mandatoryError)
    override fun validate(): Boolean {
        if (invalidValue != null) return value != invalidValue
        return when(value) {
            is String -> (value as String).trim().isNotEmpty()
            is Byte, Short, Int   -> (value as Int) != 0
            is Long   -> (value as Long) != 0L
            is Float  -> (value as Float) != 0f
            is Double -> (value as Double) != 0.0
            else -> value != null
        }
    }
}
