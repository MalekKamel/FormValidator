package com.sha.formvalidator.factory

import android.content.Context
import com.sha.formvalidator.*
import com.sha.formvalidator.core.validator.DummyValidator
import com.sha.formvalidator.core.validator.Validator
import com.sha.formvalidator.model.AttrInfo
import com.sha.formvalidator.model.ValidationType

object TextAttrValidatorFactory: AttrValidatorFactory<String> {
    override fun make(attrInfo: AttrInfo, context: Context): Validator<String> {
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