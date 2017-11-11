package com.sha.kamel.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sha.kamel.formvalidator.FormValidator;
import com.sha.kamel.sample.validator.AgeValidator;
import com.sha.kamel.sample.validator.AreaValidator;
import com.sha.kamel.sample.validator.MobileValidator;
import com.sha.kamel.sample.validator.NameValidator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Example1Activity extends BaseExampleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        formValidator.with(btn_submit)
                .add(
                        new NameValidator(et_name),
                        new AgeValidator(et_age),
                        new MobileValidator(et_mobile),
                        new AreaValidator(et_area))
                .map(validator -> new ClientInfo()
                        .setName(validator.from(et_name))
                        .setAge(validator.from(et_name))
                        .setMobile(validator.from(et_name))
                        .setArea(validator.from(et_name)))
                .doIfInvalid(() -> toast("Fill required data."))
                .asObservable()
                .doOnNext(data -> toast("Saving Client info"))
//               .flatMap(data -> {}) --> save in database
//               .flatMap(data -> {}) --> send to server
                .subscribe(
                        data -> toast("Saved data successfully."),
                        Throwable::printStackTrace);
    }

}

