package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.SuffixValidator
import com.sha.formvalidator.core.validator.TextValidator
import org.junit.Before
import org.junit.Test

class SuffixValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = SuffixValidator("suffix")
    }

    @Test
    fun `should be valid`() {
        validator.value = "XXsuffix"
        assert(validator.isValid)
    }

    @Test
    fun `should be valid if ignoreCase = true`() {
        validator = SuffixValidator("suffix", true)
        validator.value = "XXsuffiX"
        assert(validator.isValid)
    }

    @Test
    fun `should be invalid`() {
        validator.value = "11"
        assert(!validator.isValid)
    }
}