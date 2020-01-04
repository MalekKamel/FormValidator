package com.sha.formvalidator.validator

import com.sha.formvalidator.core.text.validator.CreditCardValidator
import com.sha.formvalidator.core.text.validator.TextValidator
import org.junit.Before
import org.junit.Test

class CreditCardValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = CreditCardValidator("Invalid!")
    }

    @Test
    fun validate_valid() {
        assert(validator.isValid("378734493671000"))
    }

    @Test
    fun validate_invalid() {
        assert(!validator.isValid("11"))
    }
}