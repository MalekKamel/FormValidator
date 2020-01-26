package com.sha.formvalidator.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar
import com.sha.formvalidator.Validatable
import com.sha.formvalidator.ValidatableWidget
import com.sha.formvalidator.handler.SeekBarValidationHandler
import com.sha.formvalidator.handler.ValidationHandler

/**
 * An implementation of [Validatable] for [AppCompatSeekBar].
 */
open class FormSeekBar: AppCompatSeekBar, ValidatableWidget<Int> {
    override lateinit var validationHandler: ValidationHandler<Int>
    override val value: Int?
        get() = progress

    constructor(context: Context) : super(context) { setup(null) }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setup(attrs) }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup(attrs)
    }

    private fun setup(attrs: AttributeSet?) {
        validationHandler = SeekBarValidationHandler(this, attrs)
        // to initialize change listener. Nothing is needed here as the implementation is in the function
        setOnSeekBarChangeListener(null)
    }

    override fun setOnSeekBarChangeListener(listner: OnSeekBarChangeListener?) {
        // we set the listener here to avoid overriding our listener by the user
        super.setOnSeekBarChangeListener(object: OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                validationHandler.onValueChanged(progress)
                listner?.onProgressChanged(seekBar, progress, fromUser)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) { listner?.onStartTrackingTouch(seekBar) }
            override fun onStopTrackingTouch(seekBar: SeekBar?) { listner?.onStopTrackingTouch(seekBar) }
        })
    }
}