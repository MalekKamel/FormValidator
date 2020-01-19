package com.sha.formvalidator.compose.widget


import androidx.compose.Composable
import androidx.compose.Recompose
import androidx.ui.core.Modifier
import androidx.ui.core.dp
import androidx.ui.core.sp
import androidx.ui.graphics.Color
import androidx.ui.layout.Spacing
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import com.sha.compoz.Switch
import com.sha.compoz.model.TextArgs
import com.sha.compoz.model.VectorArgs
import com.sha.formvalidator.compose.ValidatableModel
import com.sha.formvalidator.compose.ModelFactory

@Composable
fun <T: ValidatableModel<Boolean>> FormSwitch(
        model: T,
        text: String = "",
        textArgs: TextArgs = TextArgs(),
        errorTextArgs: TextArgs = TextArgs(style = TextStyle(color = Color.Red, fontSize = 18.sp)),
        vectorArgs: VectorArgs = VectorArgs(),
        modifier: Modifier = Modifier.None,
        onSelected: ((Boolean) -> Unit)? = null
) {
    Recompose { recompose ->
        FormContainer(
                model = model,
                recompose = recompose
        ) {
            Switch(
                    checked = model.value ?: false,
                    text = text,
                    textArgs = textArgs,
                    vectorArgs = vectorArgs,
                    modifier = modifier,
                    error = model.createError(),
                    errorTextArgs = errorTextArgs,
                    onCheckedChange = {
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
        FormSwitch(
                model = ModelFactory.boolean(true),
                text = "Toggle Button",
                modifier = Spacing(32.dp)
        )
    }
}
