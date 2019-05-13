package com.sha.formvalidator.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import com.sha.formvalidator.Validatable;
import com.sha.formvalidator.validation.DefaultEditTextValidator;
import com.sha.formvalidator.validation.EditTextValidator;

/**
 * AutoCompleteTextView Extension with validation.
 *
 */
public class FormAutoCompleteTextView extends AppCompatAutoCompleteTextView
        implements Validatable {

    public EditTextValidator validator;

    public FormAutoCompleteTextView(Context context) {
        super(context);
        setupDefaultValidator(null, context);
    }

    public FormAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDefaultValidator(attrs, context);
    }

    public FormAutoCompleteTextView(Context context, AttributeSet attrs, int defStyle) {
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
     * Add a validator to this AutoCompleteTextView. The validator will be added in the
     * queue of the current validators.
     *
     * @param validator object
     */
    public void addValidator(com.sha.formvalidator.validator.Validator validator){
        this.validator.addValidator(validator);
    }

    public void setValidator(EditTextValidator validator) {
        this.validator = validator;
    }

    /**
     * Calling *validate()* will cause the AutoCompleteTextView to go through
     * customValidators and call {@link com.sha.formvalidator.validator.Validator#isValid(EditText)}
     *
     * @return true if the validity passes false otherwise.
     */
    public boolean validate() {
        return validator.validate();
    }





}
