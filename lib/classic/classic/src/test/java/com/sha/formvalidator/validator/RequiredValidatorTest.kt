package com.sha.formvalidator.validator

import com.sha.formvalidator.core.validator.RequiredValidator
import com.sha.formvalidator.core.validator.TextValidator
import org.junit.Before
import org.junit.Test

class RequiredValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = RequiredValidator("Invalid!")
    }

    @Test
    fun validate_valid() {
        assert(validator.isValid("378734493671000"))
    }

    @Test
    fun validate_invalid() {
        assert(!validator.isValid(""))
    }
}