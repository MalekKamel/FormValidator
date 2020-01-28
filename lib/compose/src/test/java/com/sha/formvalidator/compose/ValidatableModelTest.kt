package com.sha.formvalidator.compose

import com.sha.formvalidator.core.validator.IntRangeValidator
import com.sha.formvalidator.core.validator.MandatoryValidator
import com.sha.formvalidator.core.validator.pattern.EmailValidator
import org.junit.Before
import org.junit.Test

class ValidatableModelTest {

    private lateinit var model: FakeValidatable
    private val error: String = "error"

    @Before
    fun setup() {
        model = FakeValidatable.create {
            errorMessage = error
            ignoreInitialValidation = false
            validateOnChange = true
        }
    }

    @Test
    fun `isValid, should be true`() {
        model.value = "value"
        assert(model.isValid)
        assert(model.status == ModelStatus.VALID)
        assert(model.createError() == null)
    }

    @Test
    fun `isValid should be false`() {
        assert(!model.isValid)
        assert(model.status == ModelStatus.INVALID)

        model.value = ""
        model.validateOnChange = false
        val e = model.createError()
        assert(e == null)

        model.value = null
        assert(model.createError() == null)
    }

    @Test
    fun `isValid should be true if isOptional = true & no value`() {
        val model: ValidationModel<String> = ValidationModel.create(MandatoryValidator())
        model.isOptional = true

        model.value = null
        assert(model.isValid)

        model.value = ""
        assert(model.isValid)
    }

    @Test
    fun `isValid should be false if isOptional = true & there's value & validators invalid`() {
        val model: ValidationModel<Int> = ValidationModel.create(IntRangeValidator(1, 5))
        model.isOptional = true

        model.value = 6
        assert(!model.isValid)
    }

    @Test
    fun `createError() error should be null if invalid and ignoreInitialValidation = true`() {
        model.value = ""
        model.ignoreInitialValidation = true
        assert(model.createError() == null)
    }

    @Test
    fun `createError() error should be null if isIgnored is true`() {
        model.isIgnored = true
        // if valid, the error must be null
        model.value = "value"
        assert(model.createError() == null)

        // if invalid, the error must be null as well
        model.value = ""
        assert(model.createError() == null)
    }

    @Test
    fun `createError() error should be null if shouldIgnore returns true`() {
        model.shouldIgnore = { true }
        // if valid, the error must be null
        model.value = "value"
        assert(model.createError() == null)

        // if invalid, the error must be null as well
        model.value = ""
        assert(model.createError() == null)
    }

    @Test
    fun `createError() error shouldn't be null when ValidationSource = USER`() {
        model.value = ""
        model.validationSource = ValidationSource.USER

        assert(model.createError() == error)
        // it shouldn't be affected with this value
        model.validateOnChange = false
        assert(model.createError() == error)
    }

    @Test
    fun `createError() error should be null when ValidationSource = LIBRARY & validateOnChange = false`() {
        model.value = ""
        model.validationSource = ValidationSource.LIBRARY

        model.validateOnChange = false
        assert(model.createError() == null)

        model.validateOnChange = true
        assert(model.createError() == error)
    }

    @Test
    fun `createError() error should be tmpError`() {
        model.value = ""
        model.tmpError = "tmp"
        assert(model.createError() == "tmp")
    }

    @Test
    fun `matches() should be valid if validators have the same value`() {
        val e = "Passwords don't match!"
        val v1 = boolean(true)
        val v2 = boolean(true)
                .matches(v1, e)

        v1.value = true
        v2.value = true

        assert(v2.createError() == null)

        assert(v1.isValid)
        assert(v2.isValid)
    }

    @Test
    fun `matches() should be invalid if validators have different values`() {
        val e = "Passwords don't match!"
        val v1 = mandatory<String>()
        val v2 = mandatory<String>()
        val v3 = mandatory<String>().matches(listOf(v1, v2), e)

        v1.ignoreInitialValidation = false
        v2.ignoreInitialValidation = false
        v3.ignoreInitialValidation = false

        v1.value = "x"
        v2.value = "y"
        v3.value = "z"

        assert(v2.createError() == e)

        assert(!v1.isValid)
        assert(!v2.isValid)
        assert(!v3.isValid)
    }

    @Test
    fun `addValidator() removeValidator(), should work correctly`() {
        assert(model.validator.validators.size == 1)

        val validator = MandatoryValidator<String>()
        model.addValidator(validator)
        assert(model.validator.validators.size == 2)

        model.addValidator(1, validator)
        assert(model.validator.validators.size == 3)
        model.removeValidator(1)

        assert(model.validator.validators.size == 2)

        model.addValidator(validator)
        model.removeValidator(1)
        assert(model.validator.validators.size == 2)

        model.addValidator(validator)
        model.removeValidator(validator)
        assert(model.validator.validators.size == 2)
    }

    @Test
    fun `addTo() should work correctly`() {
        val form  = Form.create {}
        model.tag = "tag"
        model.addTo(form)

        assert(form.modelByTag("tag") != null)
    }

    @Test
    fun `test showError`() {
        val model = FakeValidatable.create()
        model.ignoreInitialValidation = false

        model.showError("error")
        // will be empty after calling isValid
        assert(model.tmpError == "error")

        assert(!model.isValid)
        // should be true, and be false after calling createErrorText()
        assert(model.tmpError == null)
        // must be called to recompose the view
        assert(model.isRecomposeInvoked)

        model.value = "x"

        model.createError()

        // valid as we assigned value x
        assert(model.isValid)
        assert(model.tmpError == null)
    }

}
