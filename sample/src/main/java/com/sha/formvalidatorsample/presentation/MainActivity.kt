package com.sha.formvalidatorsample.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

import com.sha.formvalidatorsample.R

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.btnFields).setOnClickListener { show(FieldsActivity::class.java) }
        findViewById<View>(R.id.btnForm).setOnClickListener { show(FormActivity::class.java) }
    }

    private fun show(clazz: Class<*>) = startActivity(Intent(this, clazz))

}
