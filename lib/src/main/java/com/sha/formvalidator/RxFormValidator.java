package com.sha.formvalidator;

import android.view.View;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.processors.PublishProcessor;

public class RxFormValidator<T extends Validatable> extends BaseFormValidator<T> {

    public RxFormValidator(List<T> fields) {
        super(fields);
    }

    @SafeVarargs
    public RxFormValidator(T... fields) {
        super(fields);
    }

    public Single<Boolean> validate(){
        return Single.just(isValidForm());
    }

    public Flowable<Boolean> validateOnClick(View view){
        PublishProcessor<Boolean> pp = PublishProcessor.create();
        view.setOnClickListener(v -> pp.onNext(isValidForm()));
        return pp;
    }



}
