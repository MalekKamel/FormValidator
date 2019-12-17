package com.sha.formvalidator.rxjava.validator

import com.sha.formvalidator.textview.validator.CreditCardValidator
import com.sha.formvalidator.textview.validator.InverseValidator
import com.sha.formvalidator.textview.validator.TextValidator
import org.junit.Before
import org.junit.Test

class InverseValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = InverseValidator(CreditCardValidator(), "Invalid!")
    }

    @Test
    fun validate_valid() {
        assert(validator.isValid("11"))
    }

    @Test
    fun validate_invalid() {
        assert(!validator.isValid("378734493671000"))
    }
}