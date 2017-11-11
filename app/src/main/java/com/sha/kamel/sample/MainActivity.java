package com.sha.kamel.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({
            R.id.btn_e1,
            R.id.btn_e2,
            R.id.btn_e3
    })
    public void onClick(View v) {
        Class clazz = null;
        switch (v.getId()) {

            case R.id.btn_e1:
                clazz = Example1Activity.class;
                break;

            case R.id.btn_e2:
                clazz = Example2Activity.class;
                break;

            case R.id.btn_e3:
                clazz = Example3Activity.class;
                break;
        }

        if (clazz != null)
            startActivity(new Intent(this, clazz));
    }

}

