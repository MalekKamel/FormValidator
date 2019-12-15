package com.sha.formvalidator.validator.pattern

import com.sha.formvalidator.textview.validator.TextValidator
import com.sha.formvalidator.textview.validator.pattern.AlphaNumericValidator
import com.sha.formvalidator.textview.validator.pattern.AlphaValidator
import com.sha.formvalidator.textview.validator.pattern.PersonFullNameValidator
import com.sha.formvalidator.textview.validator.pattern.PersonNameValidator
import org.junit.Before
import org.junit.Test

class PersonFullNameValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = PersonFullNameValidator("Invalid!")
    }

    @Test
    fun validate_valid() {
        assert(validator.isValid("Shaban Kamel"))
    }

    @Test
    fun validate_invalid() {
        assert(!validator.isValid("Shaban 123"))
    }
}