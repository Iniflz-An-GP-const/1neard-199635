package shejianguo.app1neard.corpapp.ui.composable.screen.shared.pulse

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import shejianguo.app1neard.corpapp.ui.composable.screen.shared.pulse.PulseTiming

private const val BAND_WIDTH_FRACTION = 0.35f
private const val BAND_ALPHA = 0.5f

private const val SWEEP_SHARE = 0.65f

@Composable
fun Modifier.pulseEffect(cornerRadius: Dp, timing: PulseTiming): Modifier {
    if (timing.isOff) return this

    val bandColor = MaterialTheme.colorScheme.surface
        .copy(alpha = (BAND_ALPHA * timing.intensity).coerceAtMost(1f))
    val sweepMs = (timing.durationMs * SWEEP_SHARE).toInt()
    val progress = rememberInfiniteTransition(label = "actionShimmer").animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = timing.cycleMs
                0f at 0
                1f at sweepMs using LinearEasing
                if (timing.cycleMs > sweepMs) 1f at timing.cycleMs
            },
            repeatMode = RepeatMode.Restart,
        ),
        label = "actionShimmerProgress",
    )

    return this
        .clip(RoundedCornerShape(cornerRadius))
        .drawWithContent {
            drawContent()

            val bandWidth = size.width * BAND_WIDTH_FRACTION
            val left = -bandWidth + (size.width + bandWidth) * progress.value

            drawRect(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color.Transparent, bandColor, Color.Transparent),
                    startX = left,
                    endX = left + bandWidth,
                ),
                topLeft = Offset(left, 0f),
                size = Size(bandWidth, size.height),
            )
        }
}
