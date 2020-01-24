package com.sha.formvalidator.factory

import android.content.Context
import com.sha.formvalidator.model.AttrInfo
import com.sha.formvalidator.model.ValidationType
import com.sha.formvalidator.boolean
import com.sha.formvalidator.core.validator.DummyValidator
import com.sha.formvalidator.core.validator.Validator

object BooleanAttrValidatorFactory: AttrValidatorFactory<Boolean> {
    override fun make(attrInfo: AttrInfo, context: Context): Validator<Boolean> {
        return when (attrInfo.validationType) {
            ValidationType.CHECKED -> boolean(true)
            ValidationType.UNCHECKED -> boolean(false)
            else -> DummyValidator()
        }
    }
}