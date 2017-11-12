package com.sha.kamel.formvalidator;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;
import com.sha.kamel.formvalidator.util.Callback;
import com.sha.kamel.formvalidator.util.Func;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Sha on 10/24/17.
 */

public class FormValidator<T> {

    private List<Validator> validators = new ArrayList<>();
    private Map<EditText, ValidationBean> beans = new HashMap<>();
    private Map<EditText, String> texts = new HashMap<>();

    private Observable<Object> source;
    private Func invalidCallback;
    private String emptyMessage = "Required";
    private FormValidatorMapperVa<T> mapperVa;
    private FormValidatorMapper<T> mapper;

    public FormValidator<T> with(View sourceView){
        with(sourceView, null);
        return this;
    }

    public FormValidator<T> with(View sourceView, Func invalidCallback){
        if (sourceView == null)
            throw  new RuntimeException("View can't be null.");

        this.source = RxView.clicks(sourceView);
        this.invalidCallback = invalidCallback;
        return this;
    }

    public FormValidator<T> add(Validator... validators){
        for (Validator validator : validators){
            add(validator);
        }
        return this;
    }

    public FormValidator<T> add(Validator validator){
        validators.add(validator);
        beans.put(validator.et, new ValidationBean(validator.et, false));
        texts.put(validator.et, "");
        return this;
    }

    public FormValidator<T> mapData(FormValidatorMapperVa<T> mapper){
        this.mapperVa = mapper;
        return this;
    }

    public FormValidator<T> map(FormValidatorMapper<T> mapper){
        this.mapper = mapper;
        return this;
    }

    public FormValidator<T> doIfInvalid(Func invalidCallback){
        this.invalidCallback = invalidCallback;
        return this;
    }

    public Observable<T> asObservable(){
        PublishSubject<T> ps = PublishSubject.create();
        source.subscribe(o -> {
                    if (isAllValid()){
                        T data = getMapperData();
                        ps.onNext(data);
                    }
                }
        );
        start();
        return ps;
    }

    private T getMapperData() {
        String[] texts = new String[validators.size()];
        for (int i = 0 ; i < texts.length ; i++){
            Validator validator = validators.get(i);
            texts[i] = validator.et.getText().toString();
        }
        T data = null;
        if (mapper != null)
            data = mapper.call(this);
        else if (mapperVa != null)
            data = mapperVa.call(texts);

        if (data == null)
            throw  new RuntimeException("'map' can't return null.");
        return data;
    }

    public void subscribe(Callback<T> callback){
        source.subscribe(o -> {
            if (isAllValid()){
                T data = getMapperData();
                callback.call(data);
            }
        });
        start();
    }

    private void start(){
        throwExceptionsIfFound();

        for (Validator validator : validators){
            validator.start(getEmptyMessage()).subscribe(bean -> {
                beans.put(bean.getEt(), bean);
                texts.put(bean.getEt(), bean.text());
            });
            validator.emitInitialValue();
        }
    }

    public FormValidator<T> emptyMessage(String emptyMessage){
        this.emptyMessage = emptyMessage;
        return this;
    }

    private void throwExceptionsIfFound() {
        if (validators.isEmpty())
            throw new RuntimeException("You must add validators, call 'add(Validator)' method.");

        if (mapperVa == null && mapper == null)
            throw new RuntimeException("Must call 'map' or 'mapData' before 'asObservable");
    }

    public boolean isAnyValid(){
        for (ValidationBean bean : new ArrayList<>(beans.values())){
            if (bean.isValid()) return true;
        }
        return false;
    }

    public boolean isAnyHasText(){
        for (ValidationBean bean : new ArrayList<>(beans.values())){
            if (!bean.getEt().getText().toString().isEmpty()) return true;
        }
        return false;
    }

    public boolean isAllValid(){
        boolean isValid = true;
        for (ValidationBean bean : new ArrayList<>(beans.values())){
            if (!bean.isValid()){
                if (bean.getEt().getText().toString().isEmpty())
                    bean.getEt().setError(getEmptyMessage());
                isValid = false;
            }
        }
        if (!isValid) invalidCallback.call();
        return isValid;
    }

    public String from(EditText et){
        return texts.get(et);
    }

    private String getEmptyMessage(){
        return TextUtils.isEmpty(emptyMessage) ? null : emptyMessage;
    }

    public void clearAll(){
        for (ValidationBean bean : new ArrayList<>(beans.values())){
            bean.getEt().setText("");
        }
    }
}
