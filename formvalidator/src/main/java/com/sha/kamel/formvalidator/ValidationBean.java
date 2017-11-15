package com.sha.kamel.formvalidator;

import android.widget.EditText;

/**
 * Created by Sha on 11/8/17.
 */

public class ValidationBean {
    private EditText et;
    private boolean isValid;
    private Validator validator;
    public ValidationBean(EditText et, boolean isValid, Validator validator) {
        this.et = et;
        this.isValid = isValid;
        this.validator = validator;
    }

    public EditText getEt() {
        return et;
    }

    public ValidationBean setEt(EditText et) {
        this.et = et;
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
        return et.getText().toString();
    }

    public Validator validator() {
        return validator;
    }

}
