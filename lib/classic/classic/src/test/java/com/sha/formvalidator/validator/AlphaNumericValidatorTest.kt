package com.sha.formvalidator.validator

import com.sha.formvalidator.core.text.validator.TextValidator
import com.sha.formvalidator.core.text.validator.pattern.AlphaNumericValidator
import org.junit.Before
import org.junit.Test

class AlphaNumericValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = AlphaNumericValidator("Invalid!")
    }

    @Test
    fun validate_valid() {
        assert(validator.isValid("rr378734493671000"))
    }

    @Test
    fun validate_invalid() {
        assert(!validator.isValid("11*%"))
    }
}