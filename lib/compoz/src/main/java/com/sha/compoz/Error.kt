package com.sha.compoz

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.sp
import androidx.ui.graphics.Color
import androidx.ui.layout.Padding
import androidx.ui.material.MaterialTheme
import androidx.ui.text.TextStyle
import com.sha.compoz.model.TextArgs

@Composable
fun Error(
        error: String?,
        textArgs: TextArgs = TextArgs(style = TextStyle(color = (+MaterialTheme.colors()).secondary, fontSize = 18.sp))
) {
    if (error.isNullOrEmpty())  return
    Padding(padding = textArgs.padding) {
        Text(text = error,
                style = textArgs.style,
                paragraphStyle = textArgs.paragraphStyle,
                softWrap = textArgs.softWrap,
                overflow = textArgs.overflow,
                maxLines = textArgs.maxLines
        )
    }
}