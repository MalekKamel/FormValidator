package com.sha.formvalidator.model

import android.widget.TextView
import com.sha.formvalidator.core.text.ValidatorFactory
import com.sha.formvalidator.core.text.validator.TextValidator

data class CompositeValidatorInfo(
       internal var validators: MutableList<TextValidator> = mutableListOf()
) {

    fun validator(vararg validators: TextValidator) {
        this.validators.addAll(validators)
    }

    fun allValid(vararg validators: TextValidator) {
        this.validators.add(ValidatorFactory.allValid(*validators))
    }

    fun anyValid(errorMessage: String, vararg validators: TextValidator) {
        this.validators.add(ValidatorFactory.anyValid(errorMessage, *validators))
    }

    fun valueMatch(errorMessage: String, vararg fields: TextView) {
        this.validators.add(ValidatorFactory.valueMatch(errorMessage, *fields))
    }

    fun passwordMatch(errorMessage: String, field1: TextView, field2: TextView) {
        this.validators.add(ValidatorFactory.passwordMatch(errorMessage, field1, field2))
    }
}