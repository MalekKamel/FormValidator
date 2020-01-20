package com.sha.formvalidator.compose

import org.junit.Test

class ComposeValidatorTest {

    @Test
    fun `constructor(List), should work correctly`() {
        val composeValidator = ComposeValidator(listOf(mandatory(), mandatory()))
        assert(composeValidator.models.size == 2)
    }

    @Test
    fun `constructor(vararg), should work correctly`() {
        val composeValidator = ComposeValidator(mandatory(), mandatory())
        assert(composeValidator.models.size == 2)
    }

    @Test
    fun `isValid, should invoke validator#isValid`() {
        val m1 = FakeValidation.create()
        val m2 = FakeValidation.create()
        val composeValidator = ComposeValidator(m1, m2)

        composeValidator.isValid

        assert(m1.isValidInvoked)
        assert(m2.isValidInvoked)
    }
}