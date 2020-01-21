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
import com.sha.compoz.CheckBox
import com.sha.compoz.model.TextArgs
import com.sha.compoz.model.Dimens
import com.sha.formvalidator.compose.ValidatableModel
import com.sha.formvalidator.compose.boolean

@Composable
fun <T: ValidatableModel<Boolean>> FormCheckBox(
        model: T,
        text: String = "",
        textArgs: TextArgs = TextArgs(),
        errorTextArgs: TextArgs = TextArgs(style = TextStyle(color = Color.Red, fontSize = 18.sp)),
        checkBoxDimens: Dimens = Dimens(width = 25.dp, height = 25.dp),
        modifier: Modifier = Modifier.None,
        onCheckedChange: ((Boolean) -> Unit)? = null
) {
    Recompose { recompose ->
        FormContainer(
                model = model,
                recompose = recompose
        ) {
            CheckBox(
                    text = text,
                    checked = model.value ?: false,
                    textArgs = textArgs,
                    checkBoxDimens = checkBoxDimens,
                    modifier = modifier,
                    error = model.createError(),
                    errorTextArgs = errorTextArgs,
                    onCheckedChange = {
                        model.value = it
                        onCheckedChange?.invoke(it)
                    }
            )
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
private fun CheckBoxPreviewTemplate(checked: Boolean) {
    Surface {
        FormCheckBox(
                model = boolean(checked),
                text = "Check Box",
                modifier = Spacing(32.dp)
        )
    }
}
