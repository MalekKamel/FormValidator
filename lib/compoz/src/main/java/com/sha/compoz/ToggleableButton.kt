package com.sha.compoz

import androidx.compose.Composable
import androidx.ui.core.Dp
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.core.sp
import androidx.ui.foundation.selection.Toggleable
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.ripple.Ripple
import androidx.ui.text.TextStyle
import com.sha.compoz.model.TextArgs

@Composable
internal fun ToggleableButton(
        value: Boolean,
        text: String = "",
        textArgs: TextArgs = TextArgs(),
        error: String? = null,
        errorTextArgs: TextArgs = TextArgs(style = TextStyle(color = Color.Red, fontSize = 18.sp)),
        onValueChange: ((Boolean) -> Unit)? = null,
        modifier: Modifier = Modifier.None,
        imagePadding: EdgeInsets,
        imageWidth: Dp,
        imageHeight: Dp,
        image: @Composable() () -> Unit
) {
    Column {
        Row(arrangement = Arrangement.Center) {
            Padding(padding = imagePadding) {
                Ripple(bounded = false) {
                    Toggleable(
                            value = value,
                            onValueChange = onValueChange
                    ) {
                        Container(modifier = modifier wraps Size(imageWidth, imageHeight) wraps Gravity.Center) {
                            image()
                        }
                    }
                }
            }
            if (textArgs.modifier == Modifier.None) textArgs.modifier = Gravity.Center
            Text(text = text,
                    modifier = textArgs.modifier,
                    style = textArgs.style,
                    paragraphStyle = textArgs.paragraphStyle,
                    softWrap = textArgs.softWrap,
                    overflow = textArgs.overflow,
                    maxLines = textArgs.maxLines)
        }
        Error(error = error, textArgs = errorTextArgs)
    }
}