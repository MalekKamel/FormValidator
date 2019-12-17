package com.sha.formvalidator.rxjava.validator

import com.sha.formvalidator.textview.validator.TextValidator
import com.sha.formvalidator.textview.validator.ValueMatchValidator
import org.junit.Before
import org.junit.Test

class ValueMatchValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
    }

    @Test
    fun validate_valid() {
        validator = ValueMatchValidator("Invalid!", "378734493671000")
        assert(validator.isValid("378734493671000"))
    }

    @Test
    fun validate_invalid() {
        validator = ValueMatchValidator("Invalid!", "11")
        assert(!validator.isValid("378734493671000"))
    }
}