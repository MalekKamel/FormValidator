package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.LongRangeValidator
import com.sha.formvalidator.core.validator.TextValidator
import org.junit.Before
import org.junit.Test

class NumericLengthRangeValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = LongRangeValidator(1, 5, "Invalid!")
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