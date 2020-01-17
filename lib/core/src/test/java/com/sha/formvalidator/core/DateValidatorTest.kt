package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.DateValidator
import com.sha.formvalidator.core.validator.TextValidator
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
        validator.value = "2019:12:14"
        assert(validator.isValid)
    }

    @Test
    fun validate_invalidIfWrong() {
        validator.value = "2019"
        assert(!validator.isValid)
    }

    @Test
    fun validate_invalidIfEmpty() {
        validator.value = ""
        assert(!validator.isValid)
    }
}