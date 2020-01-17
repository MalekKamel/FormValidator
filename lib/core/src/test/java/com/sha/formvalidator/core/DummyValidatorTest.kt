package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.DummyValidator
import com.sha.formvalidator.core.validator.TextValidator
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
        validator.value = "kkkkk"
        assert(validator.isValid)
    }

    @Test
    fun validate_validIfEmpty() {
        validator.value = ""
        assert(validator.isValid)
    }
}