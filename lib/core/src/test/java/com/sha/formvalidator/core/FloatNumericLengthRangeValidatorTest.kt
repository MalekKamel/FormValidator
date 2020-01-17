package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.FloatRangeValidator
import com.sha.formvalidator.core.validator.TextValidator
import org.junit.Before
import org.junit.Test

class FloatNumericLengthRangeValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = FloatRangeValidator(1.0f, 5.0f, "Invalid!")
    }

    @Test
    fun validate_valid() {
        validator.value = "1"
        assert(validator.isValid)
    }

    @Test
    fun validate_invalid() {
        validator.value = "6"
        assert(!validator.isValid)
    }
}