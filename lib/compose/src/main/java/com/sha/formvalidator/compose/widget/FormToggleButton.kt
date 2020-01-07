package com.sha.formvalidator.compose.widget


import androidx.compose.Composable
import androidx.compose.Recompose
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
import com.sha.compoz.ToggleButton
import com.sha.compoz.model.TextArgs
import com.sha.compoz.model.VectorArgs
import com.sha.formvalidator.compose.ValidatableModel
import com.sha.formvalidator.compose.Validation

@Composable
fun <T: ValidatableModel<Boolean>> FormToggleButton(
        model: T,
        text: String = "",
        textArgs: TextArgs = TextArgs(),
        errorTextArgs: TextArgs = TextArgs(style = TextStyle(color = Color.Red, fontSize = 18.sp)),
        vectorArgs: VectorArgs = VectorArgs(),
        checkedImage: @Composable() () -> Unit = { DrawVector(+vectorResource(com.sha.compoz.R.drawable.ic_switch_on_def)) },
        uncheckedImage: @Composable() () -> Unit = { DrawVector(+vectorResource(com.sha.compoz.R.drawable.ic_switch_off_def)) },
        modifier: Modifier = Modifier.None,
        onSelected: ((Boolean) -> Unit)? = null,
        selected: Boolean = false
) {
    Recompose { recompose ->
        FormContainer(
                model = model,
                recompose = recompose
        ) {
            ToggleButton(
                    value = model.value,
                    selected = selected,
                    text = text,
                    textArgs = textArgs,
                    checkedImage = checkedImage,
                    uncheckedImage = uncheckedImage,
                    vectorArgs = vectorArgs,
                    modifier = modifier,
                    error = model.createErrorText(),
                    errorTextArgs = errorTextArgs,
                    onValueChange = {
                        model.value = it
                        onSelected?.invoke(it)
                    }
            )
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
        FormToggleButton(
                model = Validation.boolean(true),
                text = "Toggle Button",
                modifier = Spacing(32.dp),
                selected = selected
        )
    }
}
