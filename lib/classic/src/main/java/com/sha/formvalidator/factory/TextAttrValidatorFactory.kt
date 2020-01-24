package com.sha.formvalidator.factory

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import com.sha.formvalidator.*
import com.sha.formvalidator.core.validator.DummyValidator
import com.sha.formvalidator.core.validator.Validator
import com.sha.formvalidator.model.AttrInfo
import com.sha.formvalidator.model.ValidationType

object TextAttrValidatorFactory: AttrValidatorFactory<String> {

    override fun make(attrInfo: AttrInfo, context: Context): Validator<String> {
        val validator = when (attrInfo.validationType) {
            ValidationType.UNKNOWN -> {
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
            ValidationType.OPTIONAL -> optional()
            ValidationType.MANDATORY -> mandatory()
            ValidationType.ALPHA -> alpha()
            ValidationType.ALPHA_NUMERIC -> alphaNumeric()
            ValidationType.NUMERIC -> numeric()
            ValidationType.REGEX -> pattern(attrInfo.regex)
            ValidationType.CREDIT_CARD -> creditCard ()
            ValidationType.EMAIL -> email()
            ValidationType.PHONE -> phone()
            ValidationType.DOMAIN_NAME -> domain()
            ValidationType.IP_ADDRESS -> ipAddress()
            ValidationType.WEB_URL -> webUrl()
            ValidationType.PERSON_NAME -> personName()
            ValidationType.PERSON_FULL_NAME -> personFullName()
            ValidationType.DATE -> date(attrInfo.dateFormat)
            ValidationType.TEXT_LENGTH -> textLength(attrInfo.min.toLong(), attrInfo.max.toLong())
            ValidationType.INT_RANGE -> wrap(intRange(attrInfo.min, attrInfo.max)) { it?.toInt() }
            ValidationType.FLOAT_RANGE -> wrap(floatRange(attrInfo.floatMin, attrInfo.floatMax)) { it?.toFloat() }
            else -> DummyValidator()
        }
    }
}