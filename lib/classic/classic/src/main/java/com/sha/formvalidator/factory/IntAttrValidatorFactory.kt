package com.sha.formvalidator.factory

import android.content.Context
import com.sha.formvalidator.*
import com.sha.formvalidator.core.validator.DummyValidator
import com.sha.formvalidator.core.validator.Validator

object IntAttrValidatorFactory: AttrValidatorFactory<Int> {
    override fun make(attrInfo: AttrInfo, context: Context): Validator<Int> {
        return when (attrInfo.validationType) {
            XmlValidationType.OPTIONAL -> optional()
            XmlValidationType.INT_RANGE -> intRange(attrInfo.min, attrInfo.max)
            XmlValidationType.MANDATORY -> mandatory()
            else -> DummyValidator()
        }
    }
}