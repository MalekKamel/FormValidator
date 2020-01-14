package com.sha.formvalidator.compose.widget

import androidx.compose.Composable
import androidx.compose.Recompose
import androidx.compose.unaryPlus
import androidx.ui.core.sp
import androidx.ui.graphics.Color
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import com.sha.compoz.SeekBar
import com.sha.compoz.model.TextArgs
import com.sha.formvalidator.compose.ValidatableModel
import com.sha.formvalidator.compose.Validation

@Composable
fun <T: ValidatableModel<String>> FormSeekBar(
        model: T,
        barColor: Color = (+MaterialTheme.colors()).secondary,
        backgroundColor: Color = (+MaterialTheme.colors()).secondaryVariant,
        tickerColor: Color = (+MaterialTheme.colors()).secondaryVariant,
        errorTextArgs: TextArgs = TextArgs(style = TextStyle(color = Color.Red, fontSize = 18.sp))
) {
    Recompose { recompose ->
        FormContainer(
                model = model,
                recompose = recompose
        ) {
            SeekBar(
                    barColor = barColor,
                    backgroundColor = backgroundColor,
                    tickerColor = tickerColor,
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
        FormSeekBar(model = Validation.mandatory())
    }
}
