package com.sha.formvalidatorsample.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast

import com.sha.formvalidator.widget.FormEditText
import com.sha.formvalidatorsample.R

class FieldActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field)

        val fieldRes = intent.getIntExtra(EXTRA_LAYOUT_RES, 0)
        val descriptionRes = intent.getIntExtra(EXTRA_LAYOUT_EXPL_STR_RES, 0)
        val title = intent.getStringExtra(EXTRA_TITLE)

        val flContainer = findViewById<FrameLayout>(R.id.fl)
        val view = LayoutInflater.from(this).inflate(fieldRes, flContainer, false)
        flContainer.addView(view)

        val tvDescription = findViewById<TextView>(R.id.tv_description)
        tvDescription.setText(descriptionRes)

        val tvTitle = findViewById<TextView>(R.id.tv_title)
        tvTitle.text = title
    }


    fun onClickValidate(v: View) {
        val fdt = findViewById<FormEditText>(R.id.et)
        if (!fdt.validate()) return

        Toast.makeText(this, "Valid", Toast.LENGTH_LONG).show()
    }

    companion object {
        private val EXTRA_LAYOUT_RES = "EXTRA_LAYOUT_RES"
        private val EXTRA_LAYOUT_EXPL_STR_RES = "EXTRA_LAYOUT_EXPL_STR_RES"
        private val EXTRA_TITLE = "EXTRA_TITLE"

        fun buildIntent(
                ctx: Context,
                title: String,
                layoutRes: Int,
                explanationString: Int
        ): Intent {
            val toRet = Intent(ctx, FieldActivity::class.java)
            toRet.putExtra(EXTRA_TITLE, title)
            toRet.putExtra(EXTRA_LAYOUT_RES, layoutRes)
            toRet.putExtra(EXTRA_LAYOUT_EXPL_STR_RES, explanationString)
            return toRet
        }
    }

}
