package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.Validator
import org.junit.Before
import org.junit.Test

class CustomValidatorTest {
    lateinit var validator: Validator<String>

    @Before
    fun setup() {
        validator = NumberOneCustomValidator()
    }

    @Test
    fun validate_valid() {
        validator.value = "1"
        assert(validator.isValid)
    }

    @Test
    fun validate_invalid() {
        validator.value = "2"
        assert(!validator.isValid)
    }
}