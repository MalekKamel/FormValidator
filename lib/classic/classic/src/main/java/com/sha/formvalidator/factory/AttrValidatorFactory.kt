package com.sha.formvalidator.factory

import android.content.Context
import com.sha.formvalidator.model.AttrInfo
import com.sha.formvalidator.core.validator.Validator

interface AttrValidatorFactory<V> {
    fun make(attrInfo: AttrInfo, context: Context): Validator<V>
}
