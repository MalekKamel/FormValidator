package com.sha.kamel.formvalidator.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import io.reactivex.subjects.PublishSubject;

public class RxChangeListener implements TextWatcher {
    PublishSubject<String> ps = PublishSubject.create();

    public PublishSubject<String> asObservable(){
        return ps;
    }

    public RxChangeListener(TextView tv) {
        tv.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        ps.onNext(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}