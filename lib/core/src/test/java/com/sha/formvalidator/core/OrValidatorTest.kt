package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.CreditCardValidator
import com.sha.formvalidator.core.validator.PrefixValidator
import com.sha.formvalidator.core.validator.composite.CompositeValidator
import com.sha.formvalidator.core.validator.composite.OrValidator
import org.junit.Before
import org.junit.Test

class OrValidatorTest {
    lateinit var validator: CompositeValidator

    @Before
    fun setup() {
       val creditValidator = CreditCardValidator()
       val prefixValidator = PrefixValidator("3787")
        validator = OrValidator(creditValidator, prefixValidator).apply { errorMessage = "Invalid!" }
    }

    @Test
    fun validate_valid() {
        validator.value = "378734493671000"
        assert(validator.isValid)
    }

    @Test
    fun validate_firstInvalid() {
        validator.value = "37873449367100099"
        assert(validator.isValid)
        assert(validator.errorMessage == "Invalid!")
    }

    @Test
    fun validate_secondInvalid() {
        validator.value = "6331101999990016"
        assert(validator.isValid)
        assert(validator.errorMessage == "Invalid!")
    }

    @Test
    fun validate_allInvalid() {
        validator.value = "63311019999900168"
        assert(!validator.isValid)
        assert(validator.errorMessage == "Invalid!")
    }
}