package com.sha.formvalidator.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRatingBar
import com.sha.formvalidator.Validatable
import com.sha.formvalidator.ValidatableWidget
import com.sha.formvalidator.handler.RatingBarValidationHandler
import com.sha.formvalidator.handler.ValidationHandlerInterface

/**
 * An implementation of [Validatable] for [AppCompatRatingBar].
 */
open class FormRatingBar: AppCompatRatingBar, ValidatableWidget<Float> {
    override lateinit var validationHandler: ValidationHandlerInterface<Float>
    override val value: Float
        get() = rating

    constructor(context: Context) : super(context) { setup(null) }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setup(attrs) }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup(attrs)
    }

    private fun setup(attrs: AttributeSet?) {
        validationHandler = RatingBarValidationHandler(this, attrs)
        // to initialize change listener. Nothing is needed here as the implementation is in the function
        setOnRatingBarChangeListener { _, _, _ ->  }
    }

    override fun setOnRatingBarChangeListener(listener: OnRatingBarChangeListener) {
        super.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            validationHandler.onValueChanged(rating)
            listener.onRatingChanged(ratingBar, rating, fromUser)
        }
    }
}