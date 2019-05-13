package com.sha.formvalidator.validator.composite;

import com.sha.formvalidator.validator.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Abstract class for a multivalidator.
 *
 * @see AndValidator
 * @see OrValidator
 */
public abstract class CompositeValidator extends Validator {
    protected final List<Validator> validators;

    public CompositeValidator(String message, Validator... validators) {
        super(message);
        if (validators == null) throw new NullPointerException("validators are null");
        this.validators = new ArrayList<>(Arrays.asList(validators));
    }

    public CompositeValidator(String message) {
        super(message);
        this.validators = new ArrayList<>();
    }

    public void enqueue(Validator newValidator) {
        validators.add(newValidator);
    }


}
