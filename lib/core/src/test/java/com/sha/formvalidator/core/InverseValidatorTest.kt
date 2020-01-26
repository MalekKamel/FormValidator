package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.CreditCardValidator
import com.sha.formvalidator.core.validator.InverseValidator
import com.sha.formvalidator.core.validator.Validator
import org.junit.Before
import org.junit.Test

class InverseValidatorTest {
    lateinit var validator: Validator<String>
    lateinit var creditValidator: CreditCardValidator

    @Before
    fun setup() {
        creditValidator = CreditCardValidator()
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

    @Test
    fun `error message should be the message of the passed validator`() {
        validator.errorMessage = "errorMessage"
        assert(validator.errorMessage == "errorMessage")
        assert(creditValidator.errorMessage == "errorMessage")
    }
}