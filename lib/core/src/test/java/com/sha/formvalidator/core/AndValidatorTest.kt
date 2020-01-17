package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.CreditCardValidator
import com.sha.formvalidator.core.validator.PrefixValidator
import com.sha.formvalidator.core.validator.composite.AndValidator
import com.sha.formvalidator.core.validator.composite.CompositeValidator
import org.junit.Before
import org.junit.Test

class AndValidatorTest {
    lateinit var validator: CompositeValidator<String>

    @Before
    fun setup() {
       val creditValidator = CreditCardValidator().apply { errorMessage = "Invalid Card!" }
        val prefixValidator = PrefixValidator("3787").apply { errorMessage = "Invalid Prefix!" }
        validator = AndValidator(
                creditValidator,
                prefixValidator)
    }

    @Test
    fun validate_valid() {
        validator.value = "378734493671000"
        assert(validator.isValid)
    }

    @Test
    fun validate_firstInvalid() {
        validator.value = "37873449367100099"
        assert(!validator.isValid)
        assert(validator.errorMessage == "Invalid Card!")
    }

    @Test
    fun validate_secondInvalid() {
        validator.value = "6331101999990016"
        assert(!validator.isValid)
        assert(validator.errorMessage == "Invalid Prefix!")
    }
}