package com.sha.formvalidatorsample.classic.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

import com.sha.formvalidatorsample.R
import com.sha.formvalidatorsample.compose.ComposeActivity

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.btnFields).setOnClickListener { show(FieldsActivity::class.java) }
        findViewById<View>(R.id.btnForm).setOnClickListener { show(FormActivity::class.java) }
        findViewById<View>(R.id.btnCompose).setOnClickListener { show(ComposeActivity::class.java) }
    }

    private fun show(clazz: Class<*>) = startActivity(Intent(this, clazz))

}
