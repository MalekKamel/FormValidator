package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.CreditCardValidator
import com.sha.formvalidator.core.validator.InverseValidator
import com.sha.formvalidator.core.validator.TextValidator
import org.junit.Before
import org.junit.Test

class InverseValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
       val creditValidator = CreditCardValidator()
        validator = InverseValidator(creditValidator)
    }

    @Test
    fun validate_valid() {
        validator.value = "11"
        assert(validator.isValid)
    }

    @Test
    fun validate_invalid() {
        validator.value = "378734493671000"
        assert(!validator.isValid)
    }
}