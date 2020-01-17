package com.sha.formvalidator.model

import android.widget.TextView
import com.sha.formvalidator.ValidatorFactory
import com.sha.formvalidator.core.validator.TextValidator

data class CompositeValidatorInfo(
       internal var validators: MutableList<TextValidator> = mutableListOf()
) {

    fun validator(vararg validators: TextValidator) {
        this.validators.addAll(validators)
    }

    fun allValid(vararg validators: TextValidator) {
        this.validators.add(ValidatorFactory.allValid(*validators))
    }

    fun anyValid(vararg validators: TextValidator) {
        this.validators.add(ValidatorFactory.anyValid( *validators))
    }

    fun valueMatch(vararg fields: TextView) {
        this.validators.add(ValidatorFactory.valueMatch(*fields))
    }

    fun passwordMatch(field1: TextView, field2: TextView) {
        this.validators.add(ValidatorFactory.passwordMatch(field1, field2))
    }
}