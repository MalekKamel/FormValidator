package com.sha.formvalidator.core

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
        validator.value = "378734493671000"
        assert(validator.isValid)
    }

    @Test
    fun validate_invalid() {
        validator.value = ""
        assert(!validator.isValid)
    }
}