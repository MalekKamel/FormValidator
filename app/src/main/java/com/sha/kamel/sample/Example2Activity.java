package com.sha.kamel.sample;

import android.os.Bundle;

import com.sha.kamel.sample.validator.AgeValidator;
import com.sha.kamel.sample.validator.AreaValidator;
import com.sha.kamel.sample.validator.MobileValidator;
import com.sha.kamel.sample.validator.NameValidator;

public class Example2Activity extends BaseExampleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        formValidator.with(btn_submit, () -> toast("Fill required data."))
                .add(
                        new NameValidator(et_name),
                        new AgeValidator(et_age),
                        new MobileValidator(et_mobile),
                        new AreaValidator(et_area))
                .mapData(texts -> new ClientInfo()
                        .setName(texts[0])
                        .setAge(texts[1])
                        .setMobile(texts[2])
                        .setArea(texts[3]))
                .subscribe(data -> {
                    toast("Saved data successfully.");
                });
    }

}

