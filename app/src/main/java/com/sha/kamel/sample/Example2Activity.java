package com.sha.kamel.sample;

import android.os.Bundle;

import com.sha.kamel.formvalidator.validator.FixedLengthValidator;
import com.sha.kamel.formvalidator.validator.RangeValidator;
import com.sha.kamel.sample.validator.MobileValidator;

public class Example2Activity extends BaseExampleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        formValidator.with(btn_submit)
                .doIfInvalid(() -> toast("Fill required data."))
                .add(
                        new RangeValidator(et_name, 4, 100).initialValue("Shaban Kamel"),
                        new FixedLengthValidator(et_age, 2),
                        new MobileValidator(et_mobile),
                        new RangeValidator(et_area, 3, 25))
                .mapIndexed(texts -> new ClientInfo()
                        .setName(texts[0])
                        .setAge(texts[1])
                        .setMobile(texts[2])
                        .setArea(texts[3]))
                .subscribe(data -> toast("Saved data successfully."));
    }

}

