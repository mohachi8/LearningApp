import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun StepProgressBar(
    currentStep: Int,
    activeColor: Color = MaterialTheme.colorScheme.secondary,
    inactiveColor: Color = Color.Gray,
    circleDiameter: Float = 30f
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        StepWithLine("1", isActive = currentStep >= 1, activeColor, inactiveColor)
        StepWithLine("2", isActive = currentStep >= 2, activeColor, inactiveColor)
        StepWithLine("3", isActive = currentStep >= 3, activeColor, inactiveColor)
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun StepWithLine(
    number: String,
    isActive: Boolean,
    activeColor: Color,
    inactiveColor: Color
) {
    val circleColor = if (isActive) activeColor else inactiveColor
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(120.dp)
            .height(40.dp)
    ) {
        // 横線を描画
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .align(Alignment.Center)
        ) {
            drawLine(
                color = inactiveColor,
                strokeWidth = 2.dp.toPx(),
                start = Offset(0f, size.height / 2),
                end = Offset(size.width, size.height / 2)
            )
        }

        // 円を描画
        NumberInCircle(
            number = number,
            color = circleColor,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun NumberInCircle(
    number: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(30.dp)
            .background(color, shape = CircleShape)
    ) {
        Text(text = number, color = MaterialTheme.colorScheme.onPrimary)
    }
}