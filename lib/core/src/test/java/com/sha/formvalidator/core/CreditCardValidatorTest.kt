package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.CreditCardValidator
import com.sha.formvalidator.core.validator.Validator
import org.junit.Before
import org.junit.Test

class CreditCardValidatorTest {
    lateinit var validator: Validator<String>

    @Before
    fun setup() {
        validator = CreditCardValidator()
    }

    @Test
    fun validate_valid() {
        validator.value = "378734493671000"
        assert(validator.isValid)
    }

    @Test
    fun validate_invalid() {
        validator.value = "11"
        assert(!validator.isValid)
    }
}