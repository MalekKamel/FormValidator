package com.sha.formvalidator.handler

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.widget.Spinner
import androidx.core.content.ContextCompat
import com.sha.formvalidator.core.R
import com.sha.formvalidator.core.validator.composite.AllValidator
import com.sha.formvalidator.factory.AttrValidatorFactory
import com.sha.formvalidator.factory.SpinnerAttrValidatorFactory

class SpinnerValidationHandler(
        override val view: Spinner,
        attrs: AttributeSet?
) : AbsValidationHandler<String>() {
    override var attrValidatorFactory: AttrValidatorFactory<String> = SpinnerAttrValidatorFactory(view)
    override val value: String
        get() = view.selectedItem?.toString() ?: ""
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