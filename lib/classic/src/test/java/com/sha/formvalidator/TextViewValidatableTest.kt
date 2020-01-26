package com.sha.formvalidator

import android.view.View
import com.sha.formvalidator.core.validator.Validator
import com.sha.formvalidator.core.validator.composite.AllValidator
import com.sha.formvalidator.factory.AttrValidatorFactory
import com.sha.formvalidator.factory.TextAttrValidatorFactory
import com.sha.formvalidator.handler.ValidationHandlerInterface
import org.junit.Before
import org.junit.Test

class TextViewValidatableTest {
    private lateinit var validatableWidget: ValidatableWidgetImpl

    @Before
    fun setup() {
        validatableWidget = ValidatableWidgetImpl()
    }

    @Test
    fun `validate(), should return correct value from TextValidationHandler`() {
        validatableWidget.isValid = true
        assert(validatableWidget.validate())

        validatableWidget.isValid = false
        assert(!validatableWidget.validate())
    }

    @Test
    fun `addValidator(), should invoke TextValidationHandler#addValidator`() {
        validatableWidget.addValidator(mandatory())
        assert(validatableWidget.addValidatorInvoked)
    }

    @Test
    fun `plus(), should invoke TextValidationHandler#addValidator`() {
        validatableWidget + mandatory()
        assert(validatableWidget.addValidatorInvoked)
    }
}

class ValidatableWidgetImpl: ValidatableWidget<String> {
    override var validationHandler: ValidationHandlerInterface<String> = TextValidationHandlerImpl()
    val addValidatorInvoked: Boolean
        get() = (validationHandler as TextValidationHandlerImpl).addValidatorInvoked

    var isValid: Boolean = false
        set(value) {
            field = value
            (validationHandler as TextValidationHandlerImpl).isValid = value
        }
    override val value: String? = "text"
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