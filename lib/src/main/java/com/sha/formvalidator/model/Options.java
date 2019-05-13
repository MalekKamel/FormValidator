package com.sha.formvalidator.model;

import android.content.Context;
import android.text.TextUtils;

import com.sha.formvalidator.R;

public class Options {
    public String errorMessage;
    public boolean required = true;
    public ValidationType validationType;
    public String customValidationType;

    public String regex;
    public String dateFormat;

    public String emptyErrorMessage;

    public int minNumber;
    public int maxNumber;

    public float floatMinNumber;
    public float floatMaxNumber;

    public String emptyErrorMessage(Context context){
        return !TextUtils.isEmpty(emptyErrorMessage) ?
                emptyErrorMessage :
                context.getString(R.string.required);
    }
}
