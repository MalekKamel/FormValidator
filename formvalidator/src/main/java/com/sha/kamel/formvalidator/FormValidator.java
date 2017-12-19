package com.sha.kamel.formvalidator;

import android.view.View;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;
import com.sha.kamel.formvalidator.util.Callback;
import com.sha.kamel.formvalidator.util.Func;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Sha on 10/24/17.
 */

public final class FormValidator<T> extends ValidationManager<T>{

    @Override
    public FormValidator<T> with(View sourceView){
        with(sourceView, null);
        return this;
    }

    @Override
    public FormValidator<T> with(View sourceView, Func invalidCallback){
        if (sourceView == null)
            throw  new RuntimeException("View can't be null.");

        this.source = RxView.clicks(sourceView);
        options.invalidCallback = invalidCallback;
        return this;
    }

    @Override
    public FormValidator<T> add(Validator... validators){
        for (Validator validator : validators){
            add(validator);
        }
        return this;
    }

    @Override
    public FormValidator<T> add(Validator validator){
        validators.add(validator);
        beans.put(validator.et, new ValidationBean(validator.et, validator.til, false, validator));
        texts.put(validator.et, "");
        return this;
    }

    @Override
    public FormValidator<T> mapData(FormValidatorMapperVa<T> mapper){
        this.mapperVa = mapper;
        return this;
    }

    @Override
    public FormValidator<T> map(FormValidatorMapper<T> mapper){
        this.mapper = mapper;
        return this;
    }

    @Override
    public Observable<T> asObservable(){
        PublishSubject<T> ps = PublishSubject.create();
        sourceDisposable = source.subscribe(o -> {
                    if (isAllValid()){
                        T data = getMapperData();
                        ps.onNext(data);
                    }
                }
        );
        start();
        return ps;
    }

    @Override
    public void subscribe(Callback<T> callback){
        source.subscribe(o -> {
            if (isAllValid()){
                T data = getMapperData();
                callback.call(data);
            }
        });
        start();
    }

    @Override
    public boolean isAnyValid(){
        for (ValidationBean bean : new ArrayList<>(beans.values())){
            if (bean.isValid()) return true;
        }
        return false;
    }

    @Override
    public boolean isAnyHasText(){
        for (ValidationBean bean : new ArrayList<>(beans.values())){
            if (!bean.getEt().getText().toString().isEmpty()) return true;
        }
        return false;
    }

    @Override
    public boolean isAllValid(){
        boolean isValid = true;
        for (ValidationBean bean : new ArrayList<>(beans.values())){
            if (!bean.isValid()){

                bean.validator().notifyEmpty();
                bean.validator().setErrorDisplayedOnSubmit(true);

                // if didn't validate on change, we must validate here to
                // show the error.
                if (!options.shouldValidateOnChange){
                    bean.setError(bean.validator().getError());
                }



                isValid = false;
            }
        }
        if (!isValid  && options.invalidCallback != null) options.invalidCallback.call();
        return isValid;
    }

    @Override
    public String from(EditText et){
        return texts.get(et);
    }

    @Override
    public void clearAll(){
        for (ValidationBean bean : new ArrayList<>(beans.values())){
            bean.getEt().setText("");
        }
    }
}
