package com.sha.kamel.formvalidator;

/**
 * Created by Sha on 11/8/17.
 */

public interface FormValidatorMapper<T> {

    T call(FormValidator<T> validator);
}
