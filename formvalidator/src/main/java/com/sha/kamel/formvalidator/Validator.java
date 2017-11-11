package com.sha.kamel.formvalidator;


import android.text.TextUtils;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Sha on 11/8/17.
 */

public abstract class Validator {
    protected EditText et;

    protected abstract boolean validate(String s);

    public Validator(EditText et) {
        this.et = et;
    }

    public final Observable<ValidationBean> start(String emptyMessage){
        return RxTextView.textChanges(et)
                .skip(1)
                .debounce(200, TimeUnit.MILLISECONDS)
                .map(CharSequence::toString)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(s -> !isEmpty(s, emptyMessage)) // If empty, stop. We don't want to add others until this succeed
                .map(isValid -> validate(et.getText().toString()))
                .map(isValid -> new ValidationBean(et, isValid));
    }

    private boolean isEmpty(String s, String emptyMessage) {
        boolean isEmpty = s.trim().isEmpty();
        if (isEmpty)
            et.setError(emptyMessage);
        return isEmpty;
    }


}
