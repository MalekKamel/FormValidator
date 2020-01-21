package com.sha.compoz


import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.sp
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Checkbox
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import com.sha.compoz.model.TextArgs
import com.sha.compoz.model.ContainerArgs

@Composable
fun CheckBox(
        checked: Boolean,
        text: String = "",
        textArgs: TextArgs = TextArgs(),
        error: String? = null,
        errorTextArgs: TextArgs = TextArgs(style = TextStyle(color = Color.Red, fontSize = 18.sp)),
        onCheckedChange: ((Boolean) -> Unit)? = null,
        checkBoxArgs: ContainerArgs = ContainerArgs(width = 25.dp, height = 25.dp),
        modifier: Modifier = Modifier.None
) {
    Column {
        Clickable(onClick = { onCheckedChange?.invoke(!checked) }) {
                Row(arrangement = Arrangement.Center) {
                    Padding(padding = checkBoxArgs.padding) {
                        Container(modifier = modifier wraps Size(checkBoxArgs.width, checkBoxArgs.height) wraps Gravity.Center) {
                            Checkbox(
                                    checked = checked,
                                    onCheckedChange = onCheckedChange
                            )
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
        }
        Error(error = error, textArgs = errorTextArgs)
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
                checked = selected,
                text = "CheckBox",
                modifier = Spacing(32.dp)
        )
    }
}
