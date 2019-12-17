package com.sha.formvalidator.rxjava.validator

import com.sha.formvalidator.textview.validator.SuffixValidator
import com.sha.formvalidator.textview.validator.TextValidator
import org.junit.Before
import org.junit.Test

class SuffixValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = SuffixValidator("suffix", "Invalid!")
    }

    @Test
    fun validate_valid() {
        assert(validator.isValid("XXsuffix"))
    }

    @Test
    fun validate_invalid() {
        assert(!validator.isValid("11"))
    }
}