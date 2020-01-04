package com.sha.formvalidator.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.SwitchCompat
import com.sha.formvalidator.core.R
import com.sha.formvalidator.Validatable
import com.sha.formvalidator.model.OnOffValidation

/**
 * An implementation of [Validatable] for [SwitchCompat]
 */
open class FormSwitch: SwitchCompat, Validatable {
    private var validation: OnOffValidation = OnOffValidation.ON
    private var originalColor: Int = -1

    constructor(context: Context) : super(context) { setup(null) }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setup(attrs) }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup(attrs)
    }

    private fun setup(attrs: AttributeSet?) {
        originalColor = currentTextColor

        // the view is added programmatically
        if (attrs == null) return

        context.obtainStyledAttributes(attrs, R.styleable.FormSwitch).run {
            val attr = getInt(R.styleable.FormSwitch_switchValidation, OnOffValidation.ON.value)
            recycle()
            validation = OnOffValidation.fromValue(attr)
        }
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormSwitch)
        validation = OnOffValidation.fromValue(typedArray.getInt(
                R.styleable.FormSwitch_switchValidation,
                OnOffValidation.ON.value))
        typedArray.recycle()
    }

    override fun validate(): Boolean {
        return when(validation) {
            OnOffValidation.ON -> {
                setTextColor(if(isChecked) originalColor else Color.RED)
                isChecked
            }

            OnOffValidation.OFF -> {
                setTextColor(if(isChecked) Color.RED  else originalColor)
                !isChecked
            }
        }
    }

}