package com.sha.kamel.sample;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sha.kamel.formvalidator.FormValidator;
import com.sha.kamel.sample.validator.AgeValidator;
import com.sha.kamel.sample.validator.AreaValidator;
import com.sha.kamel.sample.validator.MobileValidator;
import com.sha.kamel.sample.validator.NameValidator;

import butterknife.ButterKnife;

public class Example3Activity extends BaseExampleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        formValidator.with(btn_submit, () -> toast("Fill required data."))
                .add(
                        new NameValidator(et_name),
                        new AgeValidator(et_age),
                        new MobileValidator(et_mobile),
                        new AreaValidator(et_area))
                .map(validator -> new ClientInfo().setArea(validator.from(et_area)))
                .doIfInvalid(() -> toast("Form is invalid."))
                .emptyMessage("Field is empty.")
                .asObservable()
                .doOnNext(data -> Log.d(getClass().getSimpleName(), "Saving Client info"))
//               .flatMap(data -> {}) --> save in database
//               .flatMap(data -> {}) --> send to server
                .subscribe(
                        data -> toast("Saved data successfully."),
                        Throwable::printStackTrace);
    }

}

