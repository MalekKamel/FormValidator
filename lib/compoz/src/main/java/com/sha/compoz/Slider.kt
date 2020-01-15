package com.sha.compoz

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Modifier
import androidx.ui.core.sp
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Slider
import androidx.ui.material.SliderPosition
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import com.sha.compoz.model.TextArgs

@Composable
fun Slider(
        position: SliderPosition,
        onValueChange: (Float) -> Unit = { position.value = it },
        modifier: Modifier = Modifier.None,
        onValueChangeEnd: () -> Unit = {},
        color: Color = (+MaterialTheme.colors()).primary,
        error: String? = null,
        errorTextArgs: TextArgs = TextArgs(style = TextStyle(color = Color.Red, fontSize = 18.sp))
) {
    Column {
        Slider(position = position,
                onValueChange = onValueChange,
                modifier = modifier,
                onValueChangeEnd = onValueChangeEnd,
                color = color
        )
        Error(error = error, textArgs = errorTextArgs)
    }
}

@Preview
@Composable
fun SeekBarPreview() {
    Slider(SliderPosition(0.5f), error = "Error")
}
