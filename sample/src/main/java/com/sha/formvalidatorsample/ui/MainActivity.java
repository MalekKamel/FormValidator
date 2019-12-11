package com.sha.formvalidatorsample.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.sha.formvalidatorsample.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnFields).setOnClickListener(__ -> show(FieldsActivity.class));
        findViewById(R.id.btnForm).setOnClickListener(__ -> show(FormActivity.class));
        findViewById(R.id.btnRxForm).setOnClickListener(__ -> show(RxFormActivity.class));
    }

    private void show(Class clazz) {
        startActivity(new Intent(this, clazz));
    }

}
