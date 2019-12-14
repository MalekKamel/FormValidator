package com.sha.formvalidator.textview

import android.widget.EditText
import com.sha.formvalidator.textview.validator.TextViewValidator
import com.sha.formvalidator.textview.validator.ValueMatchValidator
import com.sha.formvalidator.textview.validator.composite.AndValidator
import com.sha.formvalidator.textview.validator.composite.OrValidator

object ValidatorFactory {

    /**
     * all validators must be valid.
     * @param validators objects
     * @return a [CompositeValidator]
     */
    fun allValid(vararg validators: TextViewValidator): TextViewValidator {
        return AndValidator(*validators)
    }

    /**
     * one validator MUST be valid.
     * @param errorMessage string
     * @param validators objects
     * @return a [CompositeValidator]
     */
    fun anyValid(errorMessage: String, vararg validators: TextViewValidator): TextViewValidator {
        return OrValidator(errorMessage, *validators)
    }

    /**
     * the value of each [EditText] must be the same.
     * @param errorMessage string
     * @param ets objects
     * @return a [CompositeValidator]
     */
    fun valueMatch(errorMessage: String, vararg ets: EditText): TextViewValidator {
        return ValueMatchValidator(errorMessage, *ets)
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
    ): TextViewValidator {
        return ValueMatchValidator(errorMessage, passwordEditText1, passwordEditText2)
    }
}
