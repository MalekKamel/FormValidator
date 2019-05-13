package com.sha.formvalidator;

import android.view.View;

import com.sha.formvalidator.listener.ValidationListener;

import java.util.List;

public class FormValidator<T extends Validatable> extends BaseFormValidator<T> {

    public FormValidator(List<T> fields) {
        super(fields);
    }

    @SafeVarargs
    public FormValidator(T... fields) {
        super(fields);
    }

    public boolean isValid(){
        return isValidForm();
    }

    public void validateOnClick(View view, ValidationListener listener){
        view.setOnClickListener(v -> listener.onValidate(isValidForm()));
    }



}
