package com.sha.formvalidator.validator

import com.sha.formvalidator.textview.validator.PrefixValidator
import com.sha.formvalidator.textview.validator.TextValidator
import org.junit.Before
import org.junit.Test

class PrefixValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = PrefixValidator("prefix", "Invalid!")
    }

    @Test
    fun validate_valid() {
        assert(validator.isValid("prefixXX"))
    }

    @Test
    fun validate_invalid() {
        assert(!validator.isValid("11"))
    }
}