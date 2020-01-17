package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.TextValidator
import com.sha.formvalidator.core.validator.ValueMatchValidator
import org.junit.Before
import org.junit.Test

class ValueMatchValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
    }

    @Test
    fun validate_valid() {
        validator = ValueMatchValidator("Invalid!", "378734493671000", "378734493671000")
        validator.value = "378734493671000"
        assert(validator.isValid)
    }

    @Test
    fun validate_invalid() {
        validator = ValueMatchValidator("Invalid!", "11", "22")
        assert(!validator.isValid)
    }
}