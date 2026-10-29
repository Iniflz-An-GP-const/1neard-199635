package shejianguo.app1neard.corpapp.ui.composable.screen.onboarding.motion

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import shejianguo.app1neard.corpapp.ui.composable.screen.onboarding.motion.MotionTiming

private const val FADE_DEPTH = 0.65f

@Composable
fun Modifier.iconMotionEffect(index: Int, timing: MotionTiming): Modifier {
    if (timing.isOff) return this

    val progress = rememberInfiniteTransition(label = "iconv_fade").animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = timing.durationMs, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset(index * timing.staggerMs, StartOffsetType.Delay),
        ),
        label = "iconv_fadeProgress",
    )

    return graphicsLayer {
        alpha = 1f - (FADE_DEPTH * timing.intensity).coerceAtMost(0.9f) * progress.value
    }
}
