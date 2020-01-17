package com.sha.formvalidator.core.pattern

import com.sha.formvalidator.core.validator.TextValidator
import com.sha.formvalidator.core.validator.pattern.PersonNameValidator
import org.junit.Before
import org.junit.Test

class PersonNameValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = PersonNameValidator("Invalid!")
    }

    @Test
    fun validate_valid() {
        validator.value = "Shaban"
        assert(validator.validate())
    }

    @Test
    fun validate_invalid() {
        validator.value = "Shaban 123"
        assert(!validator.validate())
    }
}