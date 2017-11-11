package com.sha.kamel.formvalidator;

import android.widget.EditText;

/**
 * Created by Sha on 11/8/17.
 */

public class ValidationBean {
    private EditText et;
    private boolean isValid;
    public ValidationBean(EditText et, boolean isValid) {
        this.et = et;
        this.isValid = isValid;
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
}
