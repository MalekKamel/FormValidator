package com.sha.formvalidator.handler

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.widget.CompoundButton
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.sha.formvalidator.core.R

import com.sha.formvalidator.core.validator.composite.AllValidator
import com.sha.formvalidator.factory.AttrValidatorFactory
import com.sha.formvalidator.factory.BooleanAttrValidatorFactory
import com.sha.formvalidator.factory.IntAttrValidatorFactory

class CompoundButtonValidationHandler(
        private var compoundButton: CompoundButton,
        attrs: AttributeSet?
) : ValidationHandlerInterface<Boolean> {
    override lateinit var validator: AllValidator<Boolean>
    override var attrValidatorFactory: AttrValidatorFactory<Boolean> = BooleanAttrValidatorFactory
    override val value: Boolean
        get() = compoundButton.isChecked
    private var originalColor: Int = -1

    init {
        originalColor = compoundButton.currentTextColor
        setup(attrs, compoundButton.context)
        setupChangeListener()
    }

    private fun setupChangeListener() {
        compoundButton.setOnCheckedChangeListener { _, isChecked ->
            onValueChanged(isChecked)
        }
    }

    override fun showError(e: String?) {
        if (e == null) {
            compoundButton.setTextColor(originalColor)
            return
        }
        compoundButton.setTextColor(Color.RED)
    }
}