package com.sha.formvalidator.factory

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import com.sha.formvalidator.*
import com.sha.formvalidator.core.validator.DummyValidator
import com.sha.formvalidator.core.validator.Validator

object TextAttrValidatorFactory: AttrValidatorFactory<String> {

    override fun make(attrInfo: AttrInfo, context: Context): Validator<String> {
        val validator = when (attrInfo.validationType) {
            XmlValidationType.UNKNOWN -> {
                if (attrInfo.customValidationType.isNotEmpty())
                    customValidator(attrInfo, context) else
                    predefinedValidator(attrInfo)
            }
            else -> predefinedValidator(attrInfo)
        }

        if (!TextUtils.isEmpty(attrInfo.errorMessage)) validator.errorMessage =  attrInfo.errorMessage

        // If the xml tells us that this is not a required field, we will add InverseValidator(RequiredValidator()).
        return if (attrInfo.required) Validators.all(mandatory(), validator)
        else validator
    }

    private fun customValidator(attrInfo: AttrInfo, context: Context): Validator<String> {
        val opt = TextViewValidators.customValidators.firstOrNull {
            it.customValidationType(context) == attrInfo.customValidationType
        }
        check(opt != null) { "couldn't find a custom validator for custom validation type: ${attrInfo.customValidationType}" }
        return opt
    }

    @SuppressLint("StringFormatMatches")
    private fun predefinedValidator(attrInfo: AttrInfo): Validator<String> {
        return when (attrInfo.validationType) {
            XmlValidationType.OPTIONAL -> optional()
            XmlValidationType.MANDATORY -> mandatory()
            XmlValidationType.ALPHA -> alpha()
            XmlValidationType.ALPHA_NUMERIC -> alphaNumeric()
            XmlValidationType.NUMERIC -> numeric()
            XmlValidationType.REGEX -> pattern(attrInfo.regex)
            XmlValidationType.CREDIT_CARD -> creditCard ()
            XmlValidationType.EMAIL -> email()
            XmlValidationType.PHONE -> phone()
            XmlValidationType.DOMAIN_NAME -> domain()
            XmlValidationType.IP_ADDRESS -> ipAddress()
            XmlValidationType.WEB_URL -> webUrl()
            XmlValidationType.PERSON_NAME -> personName()
            XmlValidationType.PERSON_FULL_NAME -> personFullName()
            XmlValidationType.DATE -> date(attrInfo.dateFormat)
            XmlValidationType.TEXT_LENGTH -> textLength(attrInfo.min.toLong(), attrInfo.max.toLong())
            XmlValidationType.INT_RANGE -> wrap(intRange(attrInfo.min, attrInfo.max)) { it?.toInt() }
            XmlValidationType.FLOAT_RANGE -> wrap(floatRange(attrInfo.floatMin, attrInfo.floatMax)) { it?.toFloat() }
            XmlValidationType.CHECKED -> wrap(boolean(true)) { it == "checked" }
            XmlValidationType.UNCHECKED -> wrap(boolean(false)) { it == "unchecked" }
            XmlValidationType.UNKNOWN -> DummyValidator()
        }
    }
}