package com.sha.kamel.formvalidator;

import android.support.design.widget.TextInputLayout;
import android.widget.TextView;

/**
 * Created by Sha on 11/8/17.
 */

public class ValidationBean {
    private TextView tv;
    private boolean isValid;
    private Validator validator;
    private TextInputLayout til;

    public ValidationBean(TextView tv, TextInputLayout til, boolean isValid, Validator validator) {
        this.tv = tv;
        this.isValid = isValid;
        this.validator = validator;
        this.til = til;
    }

    public TextView getTv() {
        return tv;
    }

    public ValidationBean setTv(TextView tv) {
        this.tv = tv;
        return this;
    }

    public boolean isValid() {
        return isValid;
    }

    public ValidationBean setValid(boolean valid) {
        isValid = valid;
        return this;
    }

    public String text(){
        return tv.getText().toString();
    }

    public Validator validator() {
        return validator;
    }

    public void setError(String error) {
        if (til == null)
            tv.setError(error);
        else
            til.setError(error);
    }

    public TextInputLayout getTil() {
        return til;
    }
}
