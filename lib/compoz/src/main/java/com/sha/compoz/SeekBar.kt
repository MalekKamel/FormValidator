package com.sha.compoz

import androidx.animation.PhysicsBuilder
import androidx.compose.Composable
import androidx.compose.memo
import androidx.compose.unaryPlus
import androidx.ui.animation.animatedFloat
import androidx.ui.core.Draw
import androidx.ui.core.PxPosition
import androidx.ui.core.dp
import androidx.ui.core.gesture.DragObserver
import androidx.ui.core.gesture.PressGestureDetector
import androidx.ui.core.gesture.RawDragGestureDetector
import androidx.ui.core.sp
import androidx.ui.engine.geometry.Offset
import androidx.ui.engine.geometry.Rect
import androidx.ui.graphics.Color
import androidx.ui.graphics.Paint
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.material.MaterialTheme
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import com.sha.compoz.model.TextArgs

@Composable
fun SeekBar(
        barColor: Color = (+MaterialTheme.colors()).secondary,
        backgroundColor: Color = (+MaterialTheme.colors()).secondaryVariant,
        tickerColor: Color = (+MaterialTheme.colors()).secondaryVariant,
        error: String? = null,
        errorTextArgs: TextArgs = TextArgs(style = TextStyle(color = Color.Red, fontSize = 18.sp))
        ) {
    val animValue = +animatedFloat(0f)
    RawDragGestureDetector(dragObserver = object : DragObserver {
        override fun onDrag(dragDistance: PxPosition): PxPosition {
            animValue.snapTo(animValue.targetValue + dragDistance.x.value)
            return dragDistance
        }
    }) {
        PressGestureDetector(
                onPress = { position ->
                    animValue.animateTo(position.x.value,
                            PhysicsBuilder(dampingRatio = 1.0f, stiffness = 1500f)
                    )
                }) {
                Column {
                    Container(height = 60.dp, expanded = true) {
                        DrawSeekBar(
                                animValue.value,
                                barColor = barColor,
                                backgroundColor = backgroundColor,
                                tickerColor = tickerColor
                        )
                    }
                    Error(error = error, textArgs = errorTextArgs)
            }
        }
    }
}

@Composable
fun DrawSeekBar(
        x: Float,
        barColor: Color,
        backgroundColor: Color,
        tickerColor: Color
) {
    val paint = +memo { Paint() }
    Draw { canvas, parentSize ->
        val centerY = parentSize.height.value / 2
        val xConstraint = x.coerceIn(0f, parentSize.width.value)
        // draw bar
        paint.color = backgroundColor
        canvas.drawRect(
                Rect(0f, centerY - 5, parentSize.width.value, centerY + 5),
                paint
        )
        paint.color = barColor
        canvas.drawRect(
                Rect(0f, centerY - 5, xConstraint, centerY + 5),
                paint
        )

        paint.color = tickerColor
        // draw ticker
        canvas.drawCircle(
                Offset(xConstraint, centerY), 40f, paint
        )
    }
}

@Preview
@Composable
fun SeekBarPreview() {
    SeekBar(error = "Error")
}
