package com.sha.formvalidator.validator

import com.sha.formvalidator.textview.validator.CreditCardValidator
import com.sha.formvalidator.textview.validator.PrefixValidator
import com.sha.formvalidator.textview.validator.TextValidator
import com.sha.formvalidator.textview.validator.composite.AndValidator
import org.junit.Before
import org.junit.Test

class AndValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = AndValidator(
                CreditCardValidator("Invalid Card!"),
                PrefixValidator("3787", "Invalid Prefix!"))
    }

    @Test
    fun validate_valid() {
        assert(validator.isValid("378734493671000"))
    }

    @Test
    fun validate_firstInvalid() {
        assert(!validator.isValid("37873449367100099"))
        assert(validator.errorMessage == "Invalid Card!")
    }

    @Test
    fun validate_secondInvalid() {
        assert(!validator.isValid("6331101999990016"))
        assert(validator.errorMessage == "Invalid Prefix!")
    }
}