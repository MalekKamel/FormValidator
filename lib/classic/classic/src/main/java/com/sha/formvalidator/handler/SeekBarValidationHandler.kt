package com.sha.formvalidator.handler

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.sha.formvalidator.core.R
import com.sha.formvalidator.core.validator.composite.AllValidator
import com.sha.formvalidator.factory.AttrValidatorFactory
import com.sha.formvalidator.factory.IntAttrValidatorFactory

class SeekBarValidationHandler(
        private var view: SeekBar,
        attrs: AttributeSet?
) : ValidationHandlerInterface<Int> {
    override lateinit var validator: AllValidator<Int>
    override var attrValidatorFactory: AttrValidatorFactory<Int> = IntAttrValidatorFactory
    override val value: Int
        get() = view.progress
    private var originalColor: Int = -1

    init {
        originalColor = (view.background as? ColorDrawable)?.color ?: Color.TRANSPARENT
        setup(attrs, view.context)
    }

    override fun showError(e: String?) {
        val color = if(e == null) originalColor else ContextCompat.getColor(view.context, R.color.red_light)
        view.setBackgroundColor(color)
    }
}