package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.PrefixValidator
import com.sha.formvalidator.core.validator.SuffixValidator
import com.sha.formvalidator.core.validator.TextValidator
import org.junit.Before
import org.junit.Test

class PrefixValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = PrefixValidator("prefix")
    }

    @Test
    fun `should be valid`() {
        validator.value = "prefixXX"
        assert(validator.isValid)
    }

    @Test
    fun `should be valid if ignoreCase = true`() {
        validator = PrefixValidator("prefix", true)
        validator.value = "PrefixXX"
        assert(validator.isValid)
    }

    @Test
    fun `should be invalid`() {
        validator.value = "11"
        assert(!validator.isValid)
    }
}