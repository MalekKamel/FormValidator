package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.LengthRangeValidator
import com.sha.formvalidator.core.validator.TextValidator
import org.junit.Before
import org.junit.Test

class LengthRangeValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = LengthRangeValidator("Invalid!", 1, 5)
    }

    @Test
    fun validate_valid() {
        validator.value = "1"
        assert(validator.isValid)
    }

    @Test
    fun validate_invalid() {
        validator.value = "123456"
        assert(!validator.isValid)
    }
}