package com.sha.formvalidator.validator

import com.sha.formvalidator.core.validator.NumericValidator
import com.sha.formvalidator.core.validator.TextValidator
import org.junit.Before
import org.junit.Test

class NumericValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = NumericValidator("Invalid!")
    }

    @Test
    fun validate_valid() {
        assert(validator.isValid("1"))
    }

    @Test
    fun validate_invalidIfWrong() {
        assert(!validator.isValid("6f"))
    }

    @Test
    fun validate_invalidIfEmpty() {
        assert(!validator.isValid(""))
    }
}