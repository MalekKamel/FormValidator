package com.sha.formvalidator.textview

import android.widget.EditText
import com.sha.formvalidator.textview.validator.TextValidator
import com.sha.formvalidator.textview.validator.ValueMatchValidator
import com.sha.formvalidator.textview.validator.composite.AndValidator
import com.sha.formvalidator.textview.validator.composite.OrValidator

/**
 * Factory for creating composite validators
 */
object ValidatorFactory {

    /**
     * all validators must be valid.
     * @param validators objects
     * @return a [CompositeValidator]
     */
    fun allValid(vararg validators: TextValidator): TextValidator {
        return AndValidator(*validators)
    }

    /**
     * one validator MUST be valid.
     * @param errorMessage string
     * @param validators objects
     * @return a [CompositeValidator]
     */
    fun anyValid(errorMessage: String, vararg validators: TextValidator): TextValidator {
        return OrValidator(errorMessage, *validators)
    }

    /**
     * the value of each [EditText] must be the same.
     * @param errorMessage string
     * @param ets objects
     * @return a [CompositeValidator]
     */
    fun valueMatch(errorMessage: String, vararg texts: String): TextValidator {
        return ValueMatchValidator(errorMessage, *texts)
    }

    /**
     * both of password EditTexts must match
     * see [.valueMatch]
     * @param errorMessage string
     * @param passwordEditText1 object
     * @param passwordEditText2 object
     * @return a [CompositeValidator]
     */
    fun passwordMatch(
            errorMessage: String,
            passwordEditText1: EditText,
            passwordEditText2: EditText
    ): TextValidator {
        return ValueMatchValidator(
                errorMessage,
                passwordEditText1.text.toString(),
                passwordEditText2.text.toString())
    }
}
