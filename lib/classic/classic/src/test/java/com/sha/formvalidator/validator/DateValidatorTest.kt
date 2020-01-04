package com.sha.formvalidator.validator

import com.sha.formvalidator.core.text.validator.DateValidator
import com.sha.formvalidator.core.text.validator.TextValidator
import org.junit.Before
import org.junit.Test

class DateValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = DateValidator("Invalid!", "YYYY:MM:DD")
    }

    @Test
    fun validate_valid() {
        assert(validator.isValid("2019:12:14"))
    }

    @Test
    fun validate_invalidIfWrong() {
        assert(!validator.isValid("2019"))
    }

    @Test
    fun validate_invalidIfEmpty() {
        assert(!validator.isValid(""))
    }
}