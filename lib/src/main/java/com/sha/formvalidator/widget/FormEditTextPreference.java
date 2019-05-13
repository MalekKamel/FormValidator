package com.sha.formvalidator.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.sha.formvalidator.validation.DefaultEditTextValidator;
import com.sha.formvalidator.validation.EditTextValidator;

/**
 * A validating {@link EditTextPreference} validation is performed when the OK
 * or AlertDialog.BUTTON_POSITIVE button is clicked. When invalid an error
 * message is displayed and the EditTextPreference is not dismissed
 */
public class FormEditTextPreference extends EditTextPreference {

    public FormEditTextPreference(Context context) {
        super(context);
        setupDefaultValidator(null, context);
    }

    public FormEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDefaultValidator(attrs, context);
    }

    public FormEditTextPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupDefaultValidator(attrs, context);
    }

    private void setupDefaultValidator(AttributeSet attrs, Context context) {
        if (attrs == null){
            //support dynamic new FormEditText(context)
            validator = new DefaultEditTextValidator(getEditText(), context);
            return;
        }

        validator = new DefaultEditTextValidator(getEditText(), attrs, context);
    }


    public EditTextValidator getValidator() {
        return validator;
    }

    public void setValidator(EditTextValidator validator) {
        this.validator = validator;
    }

    @Override
    protected void showDialog(Bundle state) {
        super.showDialog(state);
        // If the dialog isn't an instance of alert dialog this code is useless
        if (!(super.getDialog() instanceof AlertDialog))  return;

        final AlertDialog dialog = (AlertDialog) super.getDialog();

        // get originalBottomPadding to know when adjust the underlying
        // layouts bottom padding (ie has room already
        // been created for an error message)
        int padding = Integer.MIN_VALUE;
        try {
            padding = ((LinearLayout) getEditText().getParent())
                    .getPaddingBottom();
        } catch (Exception e) {
            // some exception thrown. Unable to do increase space for error
            // message
        }

        final int originalBottomPadding = padding;

        Button b = dialog.getButton(AlertDialog.BUTTON_POSITIVE);

        // attach our validating on click listener
        ValidatingOnClickListener validatingOnClickListener = new ValidatingOnClickListener(
                originalBottomPadding, dialog);
        b.setOnClickListener(validatingOnClickListener);

        // add an editor action listener for the 'done/next' buttons on a
        // soft keyboard
        getEditText().setOnEditorActionListener(validatingOnClickListener);
    }

    private final class ValidatingOnClickListener implements
            View.OnClickListener, OnEditorActionListener {

        private ValidatingOnClickListener(
                int originalBottomPadding,
                AlertDialog dialog
        ) {
            this.originalBottomPadding = originalBottomPadding;
            this.dialog = dialog;
        }

        public void onClick(View view) {
            performValidation(dialog, originalBottomPadding);
        }

        private final int originalBottomPadding;

        private final AlertDialog dialog;

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            performValidation(dialog, originalBottomPadding);
            return true;
        }

    }

    private EditTextValidator validator;

    private void performValidation(AlertDialog dialog, int originalBottomPadding) {
        getEditText().setError(null);

        if (validator.validate()) {
            // Dismiss once everything is OK.

            dialog.dismiss();

            onClick(dialog, AlertDialog.BUTTON_POSITIVE);

            // reset padding - for when dialog is used again
            if (originalBottomPadding != Integer.MIN_VALUE) {
                LinearLayout parentLayout = (LinearLayout) getEditText()
                        .getParent();

                if (originalBottomPadding == parentLayout
                        .getPaddingBottom()) {
                    parentLayout.setPadding(parentLayout.getPaddingLeft(),
                            parentLayout.getPaddingTop(),
                            parentLayout.getPaddingRight(),
                            originalBottomPadding);
                }
            }

            return;
        }

        // increase padding so error message doesn't cover buttons
        if (originalBottomPadding != Integer.MIN_VALUE) {
            LinearLayout parentLayout = (LinearLayout) getEditText()
                    .getParent();

            if (originalBottomPadding == parentLayout.getPaddingBottom()) {
                parentLayout
                        .setPadding(
                                parentLayout.getPaddingLeft(),
                                parentLayout.getPaddingTop(),
                                parentLayout.getPaddingRight(),
                                (int) (parentLayout.getPaddingBottom() + getEditText()
                                        .getHeight() * 1.05));
            }
        }

    }

}
