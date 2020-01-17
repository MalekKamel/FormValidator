package com.sha.formvalidator.core.pattern

import com.sha.formvalidator.core.validator.TextValidator
import com.sha.formvalidator.core.validator.pattern.PersonFullNameValidator
import org.junit.Before
import org.junit.Test

class PersonFullNameValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = PersonFullNameValidator("Invalid!")
    }

    @Test
    fun validate_valid() {
        validator.value = "Shaban Kamel"
        assert(validator.validate())
    }

    @Test
    fun validate_invalid() {
        validator.value = "Shaban 123"
        assert(!validator.validate())
    }
}