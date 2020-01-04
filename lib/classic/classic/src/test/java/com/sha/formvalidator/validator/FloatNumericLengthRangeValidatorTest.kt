package com.sha.formvalidator.validator

import com.sha.formvalidator.core.text.validator.FloatNumericRangeValidator
import com.sha.formvalidator.core.text.validator.TextValidator
import org.junit.Before
import org.junit.Test

class FloatNumericLengthRangeValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = FloatNumericRangeValidator("Invalid!", 1.0, 5.0)
    }

    @Test
    fun validate_valid() {
        assert(validator.isValid("1"))
    }

    @Test
    fun validate_invalid() {
        assert(!validator.isValid("6"))
    }
}