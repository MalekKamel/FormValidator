package com.sha.formvalidator.core.composite

import com.sha.formvalidator.core.validator.CreditCardValidator
import com.sha.formvalidator.core.validator.PrefixValidator
import com.sha.formvalidator.core.validator.composite.CompositeValidator
import com.sha.formvalidator.core.validator.composite.OptionalValidator
import org.junit.Before
import org.junit.Test

class OptionalValidatorTest {
    lateinit var validator: CompositeValidator<String>

    @Before
    fun setup() {
       val creditValidator = CreditCardValidator().apply { errorMessage = "Invalid Card!" }
        val prefixValidator = PrefixValidator("3787").apply { errorMessage = "Invalid Prefix!" }
        validator = OptionalValidator(
                creditValidator,
                prefixValidator)
    }

    @Test
    fun `should be valid if there's no value`() {
        validator.value = null
        assert(validator.isValid)
    }

    @Test
    fun `should be valid if there's value and all validators valid`() {
        validator.value = "378734493671000"
        assert(validator.isValid)
    }

    @Test
    fun `should be invalid if there's value and all validators invalid`() {
        validator.value = "6331101999990016"
        assert(!validator.isValid)
    }

    @Test
    fun `should be invalid if there's value and some validators invalid`() {
        validator.value = "37873449367100099"
        assert(!validator.isValid)
    }
}