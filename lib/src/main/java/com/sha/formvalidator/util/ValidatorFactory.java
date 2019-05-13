package com.sha.formvalidator.util;

import android.widget.EditText;

import com.sha.formvalidator.validator.ValueMatchValidator;
import com.sha.formvalidator.validator.Validator;
import com.sha.formvalidator.validator.composite.AndValidator;
import com.sha.formvalidator.validator.composite.OrValidator;

public class ValidatorFactory {

    /**
     * all validators must be valid.
     * @param validators objects
     * @return a {@link com.sha.formvalidator.validator.composite.CompositeValidator}
     */
    public static Validator allValid(Validator...validators){
        return new AndValidator(validators);
    }

    /**
     * one validator MUST be valid.
     * @param errorMessage string
     * @param validators objects
     * @return a {@link com.sha.formvalidator.validator.composite.CompositeValidator}
     */
    public static Validator anyValid(String errorMessage, Validator...validators){
        return new OrValidator(errorMessage, validators);
    }

    /**
     * the value of each {@link EditText} must be the same.
     * @param errorMessage string
     * @param ets objects
     * @return a {@link com.sha.formvalidator.validator.composite.CompositeValidator}
     */
 public static Validator valueMatch(String errorMessage, EditText...ets){
        return new ValueMatchValidator(errorMessage, ets);
    }

    /**
     * both of password EditTexts must match
     * see {@link #valueMatch(String, EditText...)}
     * @param errorMessage string
     * @param passwordEditText1 object
     * @param passwordEditText2 object
     * @return a {@link com.sha.formvalidator.validator.composite.CompositeValidator}
     */
 public static Validator passwordMatch(
            String errorMessage,
            EditText passwordEditText1,
            EditText passwordEditText2
    ){
        return new ValueMatchValidator(errorMessage, passwordEditText1, passwordEditText2);
    }
}
