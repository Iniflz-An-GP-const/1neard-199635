package shejianguo.app1neard.corpapp.ui.composable.screen.onboarding.background

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

private const val PrimaryAlpha = 0.22f
private const val SecondaryAlpha = 0.16f

private const val PrimaryShift = 0.14f
private const val SecondaryShift = 0.24f

@Composable
fun OnboardingBackground(
    pageFraction: () -> Float,
    modifier: Modifier = Modifier,
) {
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.tertiary

    Canvas(modifier = modifier.fillMaxSize()) {
        val page = pageFraction()

        drawGlow(
            color = primary.copy(alpha = PrimaryAlpha),
            center = Offset(size.width * (0.15f - PrimaryShift * page), size.height * 0.18f),
            radius = size.width * 0.8f,
        )
        drawGlow(
            color = secondary.copy(alpha = SecondaryAlpha),
            center = Offset(size.width * (0.95f - SecondaryShift * page), size.height * 0.78f),
            radius = size.width * 0.7f,
        )
    }
}

private fun DrawScope.drawGlow(
    color: Color,
    center: Offset,
    radius: Float,
) {
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(color, Color.Transparent),
            center = center,
            radius = radius,
        ),
        radius = radius,
        center = center,
    )
}
