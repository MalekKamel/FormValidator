package com.sha.kamel.formvalidator;

import android.text.TextUtils;

import com.sha.kamel.formvalidator.util.Func;

/**
 * Created by Sha on 11/14/17.
 */

public class DefaultErrors {
    public String emptyMessage = "Required";
    public String passwordsNotIdenticalMessage = "Passwords aren't identical.";
    public boolean shouldSkipInitialValidation;
    public Func invalidCallback;
    public boolean shouldValidateOnChange;

    protected String emptyMessage(){
        return TextUtils.isEmpty(emptyMessage) ? null : emptyMessage;
    }

}
