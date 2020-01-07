package com.sha.formvalidator.compose.widget


import androidx.compose.Composable
import androidx.compose.Recompose
import androidx.compose.unaryPlus
import androidx.ui.core.Dp
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.selection.Toggleable
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.layout.*
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import com.sha.formvalidator.compose.R
import com.sha.formvalidator.compose.TextArgs
import com.sha.formvalidator.compose.ValidatableModel
import com.sha.formvalidator.compose.Validation

@Composable
fun <T: ValidatableModel<Boolean>> FormToggleButton(
        model: T,
        text: String = "",
        textArgs: TextArgs = TextArgs(),
        errorTextArgs: TextArgs = TextArgs(),
        checkedVectorImage: VectorAsset = +vectorResource(R.drawable.ic_switch_on_def),
        uncheckedVectorImage: VectorAsset = +vectorResource(R.drawable.ic_switch_off_def),
        drawableWidth: Dp = 40.dp,
        drawableHeight: Dp = 40.dp,
        drawablePadding: EdgeInsets = EdgeInsets(right = 8.dp),
        modifier: Modifier = Modifier.None,
        onSelected: ((Boolean) -> Unit)? = null,
        selected: Boolean = false
) {

    Recompose {recompose ->
        FormContainer(
                model = model,
                recompose = recompose,
                errorTextArgs = errorTextArgs) {
            Row {
                Padding(padding = drawablePadding) {
                    Ripple(bounded = false) {
                        Toggleable(
                                value = model.value,
                                onValueChange = {
                                    model.value = it
                                    onSelected?.invoke(it)
                                }) {
                            Container(modifier = modifier wraps Size(drawableWidth, drawableHeight)) {
                                if (selected) {
                                    DrawToggleButtonOn(checkedVectorImage)
                                } else {
                                    DrawToggleButtonOff(uncheckedVectorImage)
                                }
                            }
                        }
                    }
                }
                if (text.isNotEmpty()){
                    Padding(padding = textArgs.padding) {
                        Text(text = text,
                                modifier = textArgs.modifier,
                                style = textArgs.style,
                                paragraphStyle = textArgs.paragraphStyle,
                                softWrap = textArgs.softWrap,
                                overflow = textArgs.overflow,
                                maxLines = textArgs.maxLines)
                    }
                }
            }
        }
    }
}

@Composable
private fun DrawToggleButtonOn(vectorImage: VectorAsset) {
    DrawVector(vectorImage)
}

@Composable
private fun DrawToggleButtonOff(vectorImage: VectorAsset) {
    DrawVector(vectorImage)
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
