package com.sha.formvalidator.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.sha.formvalidator.Validatable;
import com.sha.formvalidator.validation.DefaultEditTextValidator;
import com.sha.formvalidator.validation.EditTextValidator;
import com.sha.formvalidator.validator.Validator;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * EditText Extension to be used in order to create forms in android.
 *
 */
public class FormEditText extends AppCompatEditText
        implements Validatable {
    private EditTextValidator validator;

    public FormEditText(Context context) {
        super(context);
        setupDefaultValidator(null, context);
    }

    public FormEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDefaultValidator(attrs, context);
    }

    public FormEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupDefaultValidator(attrs, context);
    }

    private void setupDefaultValidator(AttributeSet attrs, Context context) {
        if (attrs == null){
            //support dynamic new FormEditText(context)
            validator = new DefaultEditTextValidator(this, context);
            return;
        }

        validator = new DefaultEditTextValidator(this, attrs, context);
    }

    /**
     * Add a validator to this FormEditText. The validator will be added in the
     * queue of the current validators.
     *
     * @param validator object
     */
    public void addValidator(Validator validator) {
        this.validator.addValidator(validator);
    }

    public EditTextValidator getValidator() {
        return validator;
    }

    public void setValidator(EditTextValidator validator) {
        this.validator = validator;
    }

    /**
     * validate field
     *
     * @return true if valid.
     */
    public boolean validate() {
        return validator.validate();
    }

    @Override
    public Drawable getBackground() {
        Drawable background = super.getBackground();
        if (background != null) {
            background.clearColorFilter();
        }
        return background;
    }
}
