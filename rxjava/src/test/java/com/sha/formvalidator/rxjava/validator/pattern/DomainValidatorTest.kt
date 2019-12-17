package com.sha.formvalidator.rxjava.validator.pattern

import com.sha.formvalidator.textview.validator.TextValidator
import com.sha.formvalidator.textview.validator.pattern.DomainValidator
import org.junit.Before
import org.junit.Test

class DomainValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = DomainValidator("Invalid!")
    }

    @Test
    fun validate_valid() {
        assert(validator.isValid("www.google.com"))
    }

    @Test
    fun validate_invalid() {
        assert(!validator.isValid("11"))
    }
}