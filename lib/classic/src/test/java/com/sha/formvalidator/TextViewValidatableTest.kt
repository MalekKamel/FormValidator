package com.sha.formvalidator

import android.content.Context
import android.view.View
import com.sha.formvalidator.core.validator.Validator
import com.sha.formvalidator.core.validator.composite.AllValidator
import com.sha.formvalidator.factory.AttrValidatorFactory
import com.sha.formvalidator.factory.TextAttrValidatorFactory
import com.sha.formvalidator.handler.ValidationHandlerInterface
import org.junit.Before
import org.junit.Test

class TextViewValidatableTest {
    lateinit var textViewValidatable: TextViewValidatableImpl
    @Before
    fun setup() {
        textViewValidatable = TextViewValidatableImpl()
    }

    @Test
    fun `validate(), should return correct value from TextValidationHandler`() {
        textViewValidatable.isValid = true
        assert(textViewValidatable.validate())

        textViewValidatable.isValid = false
        assert(!textViewValidatable.validate())
    }

    @Test
    fun `addValidator(), should invoke TextValidationHandler#addValidator`() {
        textViewValidatable.addValidator(mandatory())
        assert(textViewValidatable.addValidatorInvoked)
    }

    @Test
    fun `plus(), should invoke TextValidationHandler#addValidator`() {
        textViewValidatable + mandatory()
        assert(textViewValidatable.addValidatorInvoked)
    }
}

class TextViewValidatableImpl: ValidatableWidget {
    override var validationHandler: ValidationHandlerInterface = TextValidationHandlerImpl()
    val addValidatorInvoked: Boolean
        get() = (validationHandler as TextValidationHandlerImpl).addValidatorInvoked

    var isValid: Boolean = false
        set(value) {
            field = value
            (validationHandler as TextValidationHandlerImpl).isValid = value
        }
    override val string: String
        get() = "text"
}

class TextValidationHandlerImpl : ValidationHandlerInterface<String> {
    var isValid: Boolean = false
    var addValidatorInvoked: Boolean = false
    override lateinit var view: View

    override fun addValidator(validator: Validator<String>) { addValidatorInvoked = true }


    override fun validate() = isValid

    override fun showError(e: String?) {}
    override var validator: AllValidator<String> = AllValidator(mandatory())
    override var attrValidatorFactory: AttrValidatorFactory<String> = TextAttrValidatorFactory
    override val value: String = ""
}