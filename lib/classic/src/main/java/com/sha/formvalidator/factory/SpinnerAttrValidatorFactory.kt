package com.sha.formvalidator.factory

import android.content.Context
import android.widget.Spinner
import com.sha.formvalidator.*
import com.sha.formvalidator.core.validator.DummyValidator
import com.sha.formvalidator.core.validator.Validator
import com.sha.formvalidator.model.AttrInfo
import com.sha.formvalidator.model.InvalidIf
import com.sha.formvalidator.model.ValidationType

class SpinnerAttrValidatorFactory(private val view: Spinner): AttrValidatorFactory<String> {
    override fun make(attrInfo: AttrInfo, context: Context): Validator<String> {
        return when (attrInfo.validationType) {
            ValidationType.OPTIONAL -> optional()
            ValidationType.MANDATORY -> {
                val invalidValue = when(attrInfo.invalidIf) {
                    InvalidIf.FIRST_ITEM_SELECTED, InvalidIf.UNKNOWN -> {
                        if (view.adapter?.count == 0) null
                        else view.adapter?.getItem(0).toString()
                    }
                    InvalidIf.NULLABLE -> null
                }
                mandatory(invalidValue)
            }
            else -> DummyValidator()
        }
    }
}