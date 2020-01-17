package com.sha.formvalidator

import android.widget.EditText
import android.widget.TextView
import com.sha.formvalidator.core.validator.TextValidator
import com.sha.formvalidator.core.validator.ValueMatchValidator
import com.sha.formvalidator.core.validator.composite.AndValidator
import com.sha.formvalidator.core.validator.composite.OrValidator

/**
 * Factory for creating composite validators
 */
object ValidatorFactory {

    /**
     * all validators must be valid.
     * @param validators objects
     * @return a [TextValidator]
     */
    fun allValid(vararg validators: TextValidator): TextValidator {
        return AndValidator(*validators)
    }

    /**
     * one validator MUST be valid.
     * @param errorMessage string
     * @param validators objects
     * @return a [TextValidator]
     */
    fun anyValid(vararg validators: TextValidator): TextValidator {
        return OrValidator(*validators)
    }

    /**
     * the value of each [EditText] must be the same.
     * @param fields [TextView]s to be validated
     * @return a [TextValidator]
     */
    fun valueMatch(vararg fields: TextView): TextValidator {
        return ValueMatchValidator { fields.map { it.text.toString() }.toList() }
    }

    /**
     * both of password EditTexts must match
     * see [.valueMatch]
     * @param errorMessage string
     * @param field1 object
     * @param field2 object
     * @return a [TextValidator]
     */
    fun passwordMatch(field1: TextView, field2: TextView): TextValidator {
        return ValueMatchValidator { listOf(field1.text.toString(), field2.text.toString())}
    }
}
