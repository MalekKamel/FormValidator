package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.TextValidator
import com.sha.formvalidator.core.validator.pattern.AlphaNumericValidator
import org.junit.Before
import org.junit.Test

class AlphaNumericValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = AlphaNumericValidator()
    }

    @Test
    fun validate_valid() {
        validator.value = "rr378734493671000"
        assert(validator.isValid)
    }

    @Test
    fun validate_invalid() {
        validator.value = "11*%"
        assert(!validator.isValid)
    }
}