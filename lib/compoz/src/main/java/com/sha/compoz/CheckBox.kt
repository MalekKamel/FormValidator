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
fun CheckBox(
        value: Boolean,
        selected: Boolean = false,
        text: String = "",
        checkedImage: @Composable() () -> Unit = { DrawVector(+vectorResource(R.drawable.ic_check_box_checked_def)) },
        uncheckedImage: @Composable() () -> Unit = { DrawVector(+vectorResource(R.drawable.ic_check_box_checked_def)) },
        textArgs: TextArgs = TextArgs(),
        error: String? = null,
        errorTextArgs: TextArgs = TextArgs(style = TextStyle(color = Color.Red, fontSize = 18.sp)),
        onValueChange: ((Boolean) -> Unit)? = null,
        vectorArgs: VectorArgs = VectorArgs(width = 25.dp, height = 25.dp),
        modifier: Modifier = Modifier.None
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
fun CheckBoxPreviewOff() {
    CheckBoxPreviewTemplate(false)
}

@Preview("On")
@Composable
fun CheckBoxPreviewOn() {
    CheckBoxPreviewTemplate(true)
}

@Composable
private fun CheckBoxPreviewTemplate(selected: Boolean) {
    Surface {
        CheckBox(
                value = selected,
                text = "CheckBox",
                modifier = Spacing(32.dp)
        )
    }
}
