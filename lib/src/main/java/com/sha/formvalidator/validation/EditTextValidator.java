package com.sha.formvalidator.validation;

import android.content.Context;
import android.text.TextWatcher;
import android.widget.EditText;

import com.sha.formvalidator.validator.Validator;

/**
 * Interface for encapsulating validation of an EditText control
 */
public interface EditTextValidator {
    /**
     * Add a validator to this FormEditText. The validator will be added in the
     * queue of the current validators.
     *
     * @param theValidator
     */
    void addValidator(Validator theValidator);

    boolean isRequired();

    /**
     * setup the {@link Validator}s
     */
    void setupValidator(Context context);

    /**
     * Calling *validate()* will cause the EditText to go through
     * customValidators and call {@link #Validator.isValid(EditText)}
     * Same as {@link #validate(boolean)} with first parameter true
     *
     * @return true if the validity passes false otherwise.
     */
    boolean validate();

    /**
     * Calling *validate()* will cause the EditText to go through
     * customValidators and call {@link #Validator.isValid(EditText)}
     *
     * @param showUIError determines if this call should show the UI error.
     * @return true if the validity passes false otherwise.
     */
    boolean validate(boolean showUIError);

    void showError();

}
