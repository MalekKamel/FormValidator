package com.sha.kamel.sample;

import android.os.Bundle;
import android.view.View;

import com.sha.kamel.formvalidator.ValidationEvent;
import com.sha.kamel.formvalidator.validator.FixedLengthValidator;
import com.sha.kamel.formvalidator.validator.RangeValidator;
import com.sha.kamel.sample.validator.MobileValidator;

import butterknife.OnClick;

public class Example5Activity extends BaseExampleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        formValidator
                .add(
                        new RangeValidator(et_name, 4, 100),
                        new FixedLengthValidator(et_age, 2),
                        new MobileValidator(et_mobile),
                        new RangeValidator(et_area, 3, 25))
                .map(validator -> new ClientInfo()
                        .setName(validator.textOf(et_name))
                        .setAge(validator.textOf(et_name))
                        .setMobile(validator.textOf(et_name))
                        .setArea(validator.textOf(et_name)))
                .doIfInvalid(() -> toast("Fill required data."))
                .asObservable()
                .doOnNext(data -> toast("Saving Client info"))
//               .flatMap(data -> {}) --> save in database
//               .flatMap(data -> {}) --> send to server
                .subscribe(
                        data -> toast("Saved data successfully."),
                        Throwable::printStackTrace);
    }

    @OnClick(R.id.btn_submit)
    public void onClick(View v) {
        super.onClick(v);
        formValidator.startValidation();
    }

}

