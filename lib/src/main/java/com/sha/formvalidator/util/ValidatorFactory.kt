package com.sha.formvalidator.util

import android.widget.EditText
import com.sha.formvalidator.validator.Validator
import com.sha.formvalidator.validator.ValueMatchValidator
import com.sha.formvalidator.validator.composite.AndValidator
import com.sha.formvalidator.validator.composite.OrValidator

object ValidatorFactory {

    /**
     * all validators must be valid.
     * @param validators objects
     * @return a [com.sha.formvalidator.validator.composite.CompositeValidator]
     */
    fun allValid(vararg validators: Validator): Validator {
        return AndValidator(*validators)
    }

    /**
     * one validator MUST be valid.
     * @param errorMessage string
     * @param validators objects
     * @return a [com.sha.formvalidator.validator.composite.CompositeValidator]
     */
    fun anyValid(errorMessage: String, vararg validators: Validator): Validator {
        return OrValidator(errorMessage, *validators)
    }

    /**
     * the value of each [EditText] must be the same.
     * @param errorMessage string
     * @param ets objects
     * @return a [com.sha.formvalidator.validator.composite.CompositeValidator]
     */
    fun valueMatch(errorMessage: String, vararg ets: EditText): Validator {
        return ValueMatchValidator(errorMessage, *ets)
    }

    /**
     * both of password EditTexts must match
     * see [.valueMatch]
     * @param errorMessage string
     * @param passwordEditText1 object
     * @param passwordEditText2 object
     * @return a [com.sha.formvalidator.validator.composite.CompositeValidator]
     */
    fun passwordMatch(
            errorMessage: String,
            passwordEditText1: EditText,
            passwordEditText2: EditText
    ): Validator {
        return ValueMatchValidator(errorMessage, passwordEditText1, passwordEditText2)
    }
}
