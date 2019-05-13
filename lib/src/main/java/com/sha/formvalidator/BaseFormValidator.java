package com.sha.formvalidator;

import java.util.Arrays;
import java.util.List;

public class BaseFormValidator<T extends Validatable> {

    private List<T> fields;

    public BaseFormValidator(List<T> fields) {
        this.fields = fields;
    }

    @SafeVarargs
    public BaseFormValidator(T... fields) {
        this.fields = Arrays.asList(fields);
    }

    protected boolean isValidForm(){
        boolean allValid = true;

        for (T field : fields)
            allValid = field.validate() && allValid;

        return allValid;
    }



}
