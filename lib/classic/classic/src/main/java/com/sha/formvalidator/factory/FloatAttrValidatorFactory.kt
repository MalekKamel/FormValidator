package com.sha.formvalidator.factory

import android.content.Context
import com.sha.formvalidator.*
import com.sha.formvalidator.core.validator.DummyValidator
import com.sha.formvalidator.core.validator.Validator
import com.sha.formvalidator.model.AttrInfo
import com.sha.formvalidator.model.ValidationType

object FloatAttrValidatorFactory: AttrValidatorFactory<Float> {
    override fun make(attrInfo: AttrInfo, context: Context): Validator<Float> {
        return when (attrInfo.validationType) {
            ValidationType.FLOAT_RANGE -> floatRange(attrInfo.floatMin, attrInfo.floatMax)
            ValidationType.OPTIONAL -> optional()
            ValidationType.MANDATORY -> mandatory()
            else -> DummyValidator()
        }
    }
}