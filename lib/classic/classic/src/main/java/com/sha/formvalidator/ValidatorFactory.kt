package com.sha.formvalidator

import android.widget.EditText
import android.widget.TextView
import com.sha.formvalidator.core.validator.TextValidator
import com.sha.formvalidator.core.validator.Validator
import com.sha.formvalidator.core.validator.ValueMatchValidator
import com.sha.formvalidator.core.validator.composite.AllValidator
import com.sha.formvalidator.core.validator.composite.AnyValidator

/**
 * Factory for creating composite validators
 */
object ValidatorFactory {

    /**
     * all validators must be valid.
     * @param validators objects
     * @return a [TextValidator]
     */
    fun <V> allValid(vararg validators: Validator<V>): Validator<V> {
        return AllValidator(*validators)
    }

    /**
     * one validator MUST be valid.
     * @param validators objects
     * @return a [TextValidator]
     */
    fun <V> anyValid(vararg validators: Validator<V>): Validator<V> {
        return AnyValidator(*validators)
    }

    /**
     * the value of each [EditText] must be the same.
     * @param fields [TextView]s to be validated
     * @return a [TextValidator]
     */
    fun <V> valueMatch(values: List<V?>): Validator<V> {
        return ValueMatchValidator { values }
    }

    /**
     * both of password EditTexts must match
     * see [.valueMatch]
     * @return a [TextValidator]
     */
    fun <V> passwordMatch(field1: V, field2: V): Validator<V> {
        return ValueMatchValidator { listOf(field1, field2)}
    }
}
