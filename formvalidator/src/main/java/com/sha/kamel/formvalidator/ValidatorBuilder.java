package com.sha.kamel.formvalidator;

import com.sha.kamel.formvalidator.util.Func;

/**
 * Created by Sha on 11/13/17.
 */

public class ValidatorBuilder<T> {

    ValidationOptions options = new ValidationOptions();

    public ValidatorBuilder<T> emptyMessage(String emptyMessage){
        options.emptyMessage = emptyMessage;
        return this;
    }

    public ValidatorBuilder<T> doIfInvalid(Func invalidCallback){
        options.invalidCallback = invalidCallback;
        return this;
    }

    public ValidatorBuilder<T> validateOnChange(){
        options.shouldValidateOnChange = true;
        return this;
    }

    public ValidationManager<T> build(){
        return new FormValidator<T>().setOptions(options);
    }

    public ValidatorBuilder<T> skipInitialValidation(boolean shouldSkip) {
        options.shouldSkipInitialValidation = shouldSkip;
        return this;
    }
}
