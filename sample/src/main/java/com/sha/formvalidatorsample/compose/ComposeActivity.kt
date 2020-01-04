package com.sha.formvalidatorsample.compose

import android.app.Activity
import android.os.Bundle
import androidx.ui.core.setContent

class ComposeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ComposeApp() }
    }

}

