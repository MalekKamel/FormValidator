package com.sha.formvalidator.factory

import android.content.Context
import com.sha.formvalidator.*
import com.sha.formvalidator.core.validator.DummyValidator
import com.sha.formvalidator.core.validator.Validator

object FloatAttrValidatorFactory: AttrValidatorFactory<Float> {
    override fun make(attrInfo: AttrInfo, context: Context): Validator<Float> {
        return when (attrInfo.validationType) {
            XmlValidationType.FLOAT_RANGE -> floatRange(attrInfo.floatMin, attrInfo.floatMax)
            XmlValidationType.OPTIONAL -> optional()
            XmlValidationType.MANDATORY -> mandatory()
            else -> DummyValidator()
        }
    }
}