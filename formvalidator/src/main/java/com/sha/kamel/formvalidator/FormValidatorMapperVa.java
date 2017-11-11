package com.sha.kamel.formvalidator;

/**
 * Created by Sha on 11/8/17.
 */

public interface FormValidatorMapperVa<T> {

    T call(String... texts);
}
