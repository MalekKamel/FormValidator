package com.sha.formvalidatorsample.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.sha.formvalidatorsample.R
import com.sha.formvalidatorsample.adapter.RecyclerAdapter
import com.sha.formvalidatorsample.util.SnackBarUtil


class FieldsActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_examples)

        setupList()

        SnackBarUtil.gotIt(
                findViewById<View>(R.id.rv),
                "Click settings icon in ToolBar to show FormEditTextPreference"
        )
    }

    private fun setupList() {
        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = RecyclerAdapter()
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.prefs -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                return true
            }

            else -> return false
        }
    }

}