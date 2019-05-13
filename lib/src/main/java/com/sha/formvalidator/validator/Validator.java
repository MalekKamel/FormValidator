package com.sha.formvalidator.validator;

import android.widget.EditText;

/**
 * Validator abstract class. To be used with FormEditText
 *
 */
public abstract class Validator {
    protected String errorMessage;

    public Validator(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Should check if the EditText is valid.
     *
     * @param et the edittext under evaluation
     * @return true if the edittext is valid, false otherwise
     */
    public abstract boolean isValid(EditText et);

    public boolean hasErrorMessage() {
        return errorMessage != null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
