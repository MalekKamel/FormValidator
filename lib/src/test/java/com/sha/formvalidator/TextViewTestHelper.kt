package com.sha.formvalidator

import android.widget.TextView
import androidx.test.core.app.ApplicationProvider

class TextViewTestHelper {
    fun mocked(text: String): TextView{
        return TextView(ApplicationProvider.getApplicationContext())
                .apply { setText(text) }
    }
}