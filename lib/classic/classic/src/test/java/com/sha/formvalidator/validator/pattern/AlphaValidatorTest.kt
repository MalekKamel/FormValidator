package com.sha.formvalidator.validator.pattern

import com.sha.formvalidator.core.validator.TextValidator
import com.sha.formvalidator.core.validator.pattern.AlphaValidator
import org.junit.Before
import org.junit.Test

class AlphaValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = AlphaValidator("Invalid!")
    }

    @Test
    fun validate_valid() {
        assert(validator.isValid("rrlll"))
    }

    @Test
    fun validate_invalid() {
        assert(!validator.isValid("11*%"))
    }
}