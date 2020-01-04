package com.sha.formvalidator.validator

import com.sha.formvalidator.core.text.validator.DummyValidator
import com.sha.formvalidator.core.text.validator.TextValidator
import org.junit.Before
import org.junit.Test

class DummyValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = DummyValidator()
    }

    @Test
    fun validate_valid() {
        assert(validator.isValid("kkkkk"))
    }

    @Test
    fun validate_validIfEmpty() {
        assert(validator.isValid(""))
    }
}