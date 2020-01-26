package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.NumericValidator
import com.sha.formvalidator.core.validator.Validator
import org.junit.Before
import org.junit.Test

class NumericValidatorTest {
    lateinit var validator: Validator<String>

    @Before
    fun setup() {
        validator = NumericValidator()
    }

    @Test
    fun validate_valid() {
        validator.value = "1"
        assert(validator.isValid)
    }

    @Test
    fun validate_invalidIfWrong() {
        validator.value = "6f"
        assert(!validator.isValid)
    }

    @Test
    fun validate_invalidIfEmpty() {
        validator.value = ""
        assert(!validator.isValid)
    }
}