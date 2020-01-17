package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.PrefixValidator
import com.sha.formvalidator.core.validator.TextValidator
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
        validator.value = "prefixXX"
        assert(validator.isValid)
    }

    @Test
    fun validate_invalid() {
        validator.value = "11"
        assert(!validator.isValid)
    }
}