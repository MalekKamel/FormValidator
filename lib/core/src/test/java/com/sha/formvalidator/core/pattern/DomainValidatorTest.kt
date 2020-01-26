package com.sha.formvalidator.core.pattern

import com.sha.formvalidator.core.validator.Validator
import com.sha.formvalidator.core.validator.pattern.DomainValidator
import org.junit.Before
import org.junit.Test

class DomainValidatorTest {
    lateinit var validator: Validator<String>

    @Before
    fun setup() {
        validator = DomainValidator()
    }

    @Test
    fun validate_valid() {
        validator.value = "www.google.com"
        assert(validator.validate())
    }

    @Test
    fun validate_invalid() {
        validator.value = "11"
        assert(!validator.validate())
    }
}