package com.sha.formvalidator.widget

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.preference.EditTextPreference
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import com.sha.formvalidator.textview.DefTextViewValidationHandler
import com.sha.formvalidator.textview.TextViewValidationHandler

/**
 * A validating [EditTextPreference] validation is performed when the OK
 * or AlertDialog.BUTTON_POSITIVE button is clicked. When invalid an error
 * message is displayed and the EditTextPreference is not dismissed
 */
open class FormEditTextPreference : EditTextPreference {

    var validationHandler: TextViewValidationHandler? = null

    constructor(context: Context) : super(context) { setupDefaultValidator(null, context) }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setupDefaultValidator(attrs, context) }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setupDefaultValidator(attrs, context)
    }

    private fun setupDefaultValidator(attrs: AttributeSet?, context: Context) {
        if (attrs == null) {
            //support dynamic new FormEditText(context)
            validationHandler = DefTextViewValidationHandler(editText, context)
            return
        }

        validationHandler = DefTextViewValidationHandler(editText, attrs, context)
    }

    override fun showDialog(state: Bundle) {
        super.showDialog(state)
        // If the dialog isn't an instance of alert dialog this code is useless
        if (super.getDialog() !is AlertDialog) return

        val dialog = super.getDialog() as AlertDialog

        // get originalBottomPadding to know when adjust the underlying
        // layouts bottom padding (ie has room already
        // been created for an error message)
        var padding = Integer.MIN_VALUE
        try {
            padding = (editText.parent as LinearLayout).paddingBottom
        } catch (e: Exception) {
            // some exception thrown. Unable to do increase space for error
            // message
        }

        val originalBottomPadding = padding
        val b = dialog.getButton(AlertDialog.BUTTON_POSITIVE)

        // attach our validating on click listener
        val validatingOnClickListener = ValidatingOnClickListener(
                originalBottomPadding, dialog)
        b.setOnClickListener(validatingOnClickListener)

        // add an editor action listener for the 'done/next' buttons on a
        // soft keyboard
        editText.setOnEditorActionListener(validatingOnClickListener)
    }

    private inner class ValidatingOnClickListener(
            private val originalBottomPadding: Int,
            private val dialog: AlertDialog
    ) : View.OnClickListener, OnEditorActionListener {

        override fun onClick(view: View) {
            performValidation(dialog, originalBottomPadding)
        }

        override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {
            performValidation(dialog, originalBottomPadding)
            return true
        }

    }

    private fun performValidation(dialog: AlertDialog, originalBottomPadding: Int) {
        editText.error = null

        if (validationHandler!!.validate()) {
            // Dismiss once everything is OK.
            dialog.dismiss()
            onClick(dialog, AlertDialog.BUTTON_POSITIVE)

            // reset padding - for when dialog is used again
            if (originalBottomPadding != Integer.MIN_VALUE) {
                val parentLayout = editText
                        .parent as LinearLayout

                if (originalBottomPadding == parentLayout.paddingBottom) {
                    parentLayout.setPadding(
                            parentLayout.paddingLeft,
                            parentLayout.paddingTop,
                            parentLayout.paddingRight,
                            originalBottomPadding)
                }
            }
            return
        }

        // increase padding so error message doesn't cover buttons
        if (originalBottomPadding != Integer.MIN_VALUE) {
            val parentLayout = editText
                    .parent as LinearLayout

            if (originalBottomPadding == parentLayout.paddingBottom) {
                parentLayout.setPadding(
                                parentLayout.paddingLeft,
                                parentLayout.paddingTop,
                                parentLayout.paddingRight,
                                (parentLayout.paddingBottom + editText.height * 1.05).toInt())
            }
        }

    }

}
