package com.sha.formvalidator.model

import com.sha.formvalidator.ValidatorFactory
import com.sha.formvalidator.core.validator.Validator

data class CompositeValidatorInfo<V>(
       internal var validators: MutableList<Validator<V>> = mutableListOf()
) {

    fun validator(vararg validators: Validator<V>) {
        this.validators.addAll(validators)
    }

    fun allValid(vararg validators: Validator<V>) {
        this.validators.add(ValidatorFactory.allValid(*validators))
    }

    fun anyValid(vararg validators: Validator<V>) {
        this.validators.add(ValidatorFactory.anyValid( *validators))
    }

    fun valueMatch(values: List<V?>) {
        this.validators.add(ValidatorFactory.valueMatch(values))
    }

    fun passwordMatch(field1: V, field2: V) {
        this.validators.add(ValidatorFactory.passwordMatch(field1, field2))
    }
}