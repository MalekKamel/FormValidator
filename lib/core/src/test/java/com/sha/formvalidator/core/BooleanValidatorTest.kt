package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.BooleanValidator
import com.sha.formvalidator.core.validator.Validator
import org.junit.Before
import org.junit.Test

class BooleanValidatorTest {
    lateinit var validator: Validator<Boolean>

    @Before
    fun setup() {
        validator = BooleanValidator(true)
    }

    @Test
    fun valid() {
        validator.value = true
        assert(validator.isValid)
    }

    @Test
    fun invalid() {
        validator.value = false
        assert(!validator.isValid)
    }
}