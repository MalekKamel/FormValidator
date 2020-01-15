package com.sha.formvalidator.compose.widget

import androidx.compose.Composable
import androidx.compose.Recompose
import androidx.compose.unaryPlus
import androidx.ui.core.Modifier
import androidx.ui.core.sp
import androidx.ui.graphics.Color
import androidx.ui.material.MaterialTheme
import androidx.ui.material.SliderPosition
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import com.sha.compoz.Slider
import com.sha.compoz.model.TextArgs
import com.sha.formvalidator.compose.ValidatableModel
import com.sha.formvalidator.compose.Validation

@Composable
fun <T: ValidatableModel<Float>> FormSlider(
        model: T,
        position: SliderPosition,
        onValueChange: (Float) -> Unit = { position.value = it },
        modifier: Modifier = Modifier.None,
        onValueChangeEnd: () -> Unit = {},
        color: Color = (+MaterialTheme.colors()).primary,
        errorTextArgs: TextArgs = TextArgs(style = TextStyle(color = Color.Red, fontSize = 18.sp))
) {
    Recompose { recompose ->
        FormContainer(
                model = model,
                recompose = recompose
        ) {
            Slider(
                    position = position,
                    onValueChange = {
                        model.value = it
                        recompose()
                        onValueChange.invoke(it)
                    },
                    modifier = modifier,
                    color = color,
                    onValueChangeEnd = onValueChangeEnd,
                    error = model.createErrorText(),
                    errorTextArgs = errorTextArgs
            )
        }
    }
}

@Preview
@Composable
private fun FormSeekBarPreview() {
    Surface {
        FormSlider(model = Validation.floatNumericRange(0.1, 0.5), position = SliderPosition())
    }
}
