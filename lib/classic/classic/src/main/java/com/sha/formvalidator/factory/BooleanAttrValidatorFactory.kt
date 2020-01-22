package com.sha.formvalidator.factory

import android.content.Context
import com.sha.formvalidator.AttrInfo
import com.sha.formvalidator.XmlValidationType
import com.sha.formvalidator.boolean
import com.sha.formvalidator.core.validator.DummyValidator
import com.sha.formvalidator.core.validator.Validator

object BooleanAttrValidatorFactory: AttrValidatorFactory<Boolean> {
    override fun make(attrInfo: AttrInfo, context: Context): Validator<Boolean> {
        return when (attrInfo.validationType) {
            XmlValidationType.CHECKED -> boolean(true)
            XmlValidationType.UNCHECKED -> boolean(false)
            else -> DummyValidator()
        }
    }
}