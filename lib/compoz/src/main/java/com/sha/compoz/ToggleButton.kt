package com.sha.compoz


import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Modifier
import androidx.ui.core.dp
import androidx.ui.core.sp
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.Spacing
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import com.sha.compoz.model.TextArgs
import com.sha.compoz.model.VectorArgs

@Composable
fun ToggleButton(
        value: Boolean,
        selected: Boolean,
        text: String = "",
        textArgs: TextArgs = TextArgs(),
        checkedImage: @Composable() () -> Unit = { DrawVector(+vectorResource(R.drawable.ic_switch_on_def)) },
        uncheckedImage: @Composable() () -> Unit = { DrawVector(+vectorResource(R.drawable.ic_switch_off_def)) },
        vectorArgs: VectorArgs = VectorArgs(),
        modifier: Modifier = Modifier.None,
        error: String? = null,
        errorTextArgs: TextArgs = TextArgs(style = TextStyle(color = Color.Red, fontSize = 18.sp)),
        onValueChange: ((Boolean) -> Unit)? = null
) {
    ToggleableButton(
            value = value,
            text = text,
            textArgs = textArgs,
            error = error,
            errorTextArgs = errorTextArgs,
            onValueChange = onValueChange,
            modifier = modifier,
            imagePadding = vectorArgs.padding,
            imageWidth = vectorArgs.width,
            imageHeight = vectorArgs.height
    ) {
        if (selected) {
            checkedImage()
        } else {
            uncheckedImage()
        }
    }
}

@Preview("Off")
@Composable
fun ToggleButtonPreviewOff() {
    ToggleButtonPreviewTemplate(false)
}

@Preview("On")
@Composable
fun ToggleButtonPreviewOn() {
    ToggleButtonPreviewTemplate(true)
}

@Composable
private fun ToggleButtonPreviewTemplate(selected: Boolean) {
    Surface {
        ToggleButton(
                value = selected,
                selected = selected,
                text = "Toggle Button",
                modifier = Spacing(32.dp)
        )
    }
}
