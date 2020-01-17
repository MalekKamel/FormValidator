package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.SuffixValidator
import com.sha.formvalidator.core.validator.TextValidator
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
        validator.value = "XXsuffix"
        assert(validator.isValid)
    }

    @Test
    fun validate_invalid() {
        validator.value = "11"
        assert(!validator.isValid)
    }
}