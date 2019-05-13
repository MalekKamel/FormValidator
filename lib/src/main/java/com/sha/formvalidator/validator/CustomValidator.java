package com.sha.formvalidator.validator;

import android.content.Context;

public abstract class CustomValidator extends Validator {

    public CustomValidator(String errorMessage) {
        super(errorMessage);
    }

    public abstract String customValidationType(Context context);

}
