package com.sha.formvalidatorsample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sha.formvalidatorsample.R;
import com.sha.formvalidatorsample.adapter.RecyclerAdapter;
import com.sha.formvalidatorsample.util.SnackBarUtil;


public class FieldsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examples);

        setupList();

        SnackBarUtil.gotIt(
                findViewById(R.id.rv),
                "Click settings icon in ToolBar to show FormEditTextPreference"
        );
    }

    private void setupList() {
        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new RecyclerAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.prefs:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;

            default:
                return false;
        }
    }

}