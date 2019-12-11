package com.sha.formvalidatorsample.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sha.formvalidator.util.ValidatorFactory;
import com.sha.formvalidator.widget.FormEditText;
import com.sha.formvalidatorsample.R;

public class PasswordValidatorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        TextView tvDescription = findViewById(R.id.tv_description);
        TextView tvTitle = findViewById(R.id.tv_title);

        tvDescription.setText(R.string.password_description);
        tvTitle.setText(R.string.passwords_match);

        //Interesting stuff starts here

        FormEditText etPassword = findViewById(R.id.etPassword);
        FormEditText etConfirmPassword = findViewById(R.id.etConfirmPassword);

        etPassword.addValidator(ValidatorFactory.passwordMatch(
                "Passwords don't match!",
                etPassword,
                etConfirmPassword
        ));
    }

    public void onClickValidate(View v) {
        FormEditText fdt = findViewById(R.id.etPassword);
        if (!fdt.validate()) return;
        Toast.makeText(this, "Valid", Toast.LENGTH_LONG).show();
    }
}
