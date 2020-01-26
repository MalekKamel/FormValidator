package com.sha.formvalidator.core.pattern

import com.sha.formvalidator.core.validator.Validator
import com.sha.formvalidator.core.validator.pattern.AlphaNumericValidator
import org.junit.Before
import org.junit.Test

class AlphaNumericValidatorTest {
    lateinit var validator: Validator<String>

    @Before
    fun setup() {
        validator = AlphaNumericValidator()
    }

    @Test
    fun validate_valid() {
        validator.value = "rrlll2233"
        assert(validator.isValid)
    }

    @Test
    fun validate_invalid() {
        validator.value = "11*%"
        assert(!validator.isValid)
    }
}