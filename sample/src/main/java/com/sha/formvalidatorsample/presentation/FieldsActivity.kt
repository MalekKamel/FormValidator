package com.sha.formvalidatorsample.presentation

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
    }

    private fun setupList() {
        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = RecyclerAdapter()
    }

}