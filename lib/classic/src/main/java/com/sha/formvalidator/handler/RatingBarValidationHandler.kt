package com.sha.formvalidator.handler

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.widget.RatingBar
import androidx.core.content.ContextCompat
import com.sha.formvalidator.core.R
import com.sha.formvalidator.core.validator.composite.AllValidator
import com.sha.formvalidator.factory.AttrValidatorFactory
import com.sha.formvalidator.factory.FloatAttrValidatorFactory

class RatingBarValidationHandler(
        override val view: RatingBar,
        attrs: AttributeSet?
) : ValidationHandlerInterface<Float> {
    override lateinit var validator: AllValidator<Float>
    override var attrValidatorFactory: AttrValidatorFactory<Float> = FloatAttrValidatorFactory
    override val value: Float
        get() {
            print("rating = ${view.rating}")
           return view.rating
        }
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