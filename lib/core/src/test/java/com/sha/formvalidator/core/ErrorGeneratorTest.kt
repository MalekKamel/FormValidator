package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.ErrorGenerator
import org.junit.Test

class ErrorGeneratorTest {

    @Test
    fun `create(string) should create instance correctly`() {
        val gen = ErrorGenerator.create("error")
        assert(gen.error() == "error")
        assert(gen.generate() == "error")
    }

    @Test
    fun `create(lambda) should create instance correctly`() {
        val gen = ErrorGenerator.create { "error" }
        assert(gen.error() == "error")
        assert(gen.generate() == "error")
    }

}