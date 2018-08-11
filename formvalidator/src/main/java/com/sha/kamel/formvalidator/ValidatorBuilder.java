package com.sha.kamel.formvalidator;

import com.sha.kamel.formvalidator.util.Procedure;

/**
 * Created by Sha on 11/13/17.
 */

public class ValidatorBuilder<T> {

    ValidationOptions options = new ValidationOptions();

    public ValidatorBuilder<T> emptyMessage(String emptyMessage){
        options.messageIfEmpty = emptyMessage;
        return this;
    }

    public ValidatorBuilder<T> doIfInvalid(Procedure invalidCallback){
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

}
