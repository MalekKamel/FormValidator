package com.sha.formvalidator.factory

import android.content.Context
import com.sha.formvalidator.*
import com.sha.formvalidator.core.validator.DummyValidator
import com.sha.formvalidator.core.validator.Validator
import com.sha.formvalidator.model.AttrInfo
import com.sha.formvalidator.model.ValidationType

object IntAttrValidatorFactory: AttrValidatorFactory<Int> {
    override fun make(attrInfo: AttrInfo, context: Context): Validator<Int> {
        return when (attrInfo.validationType) {
            ValidationType.OPTIONAL -> optional()
            ValidationType.INT_RANGE -> intRange(attrInfo.min, attrInfo.max)
            ValidationType.MANDATORY -> mandatory()
            else -> DummyValidator()
        }
    }
}