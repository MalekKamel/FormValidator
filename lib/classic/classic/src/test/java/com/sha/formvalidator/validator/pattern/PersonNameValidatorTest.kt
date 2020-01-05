package com.sha.formvalidator.validator.pattern

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
        assert(validator.isValid("Shaban"))
    }

    @Test
    fun validate_invalid() {
        assert(!validator.isValid("Shaban 123"))
    }
}